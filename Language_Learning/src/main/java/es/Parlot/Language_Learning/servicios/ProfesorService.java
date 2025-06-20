package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.repositorios.ProfesorRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    IdiomaService idiomaService;

    @Autowired
    TipoClaseService tipoClaseService;

    public List<Profesor> getAll(){
        return profesorRepositorio.findAll();
    }

    @Autowired
    private UsuarioService Usuario;

    public Profesor getProfesorById(Integer id) {
        return profesorRepositorio.findById(id).get();
    }

    public Profesor getProfesorByUser(String user) {
        return getProfesorById(Usuario.getByUsername(user).getId());
    }


    public List<Profesor> buscarProfesores(Integer paisId, Double max, Double min, Integer tipoId, Integer idiomaHablaId, Integer idiomaEnseñaId){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profesor> cq = cb.createQuery(Profesor.class);

        Root<Profesor> profesorRoot = cq.from(Profesor.class);
        Join<Profesor, Usuario> usuarioJoin = profesorRoot.join("user");

        List<Predicate> predicates = new ArrayList<>();

        if (paisId != null) {
            predicates.add(cb.equal(usuarioJoin.get("pais").get("id"), paisId));
        }
        if (idiomaHablaId != null) {
            Idioma idiomaHabla = idiomaService.findById(idiomaHablaId);
            predicates.add(cb.isMember(idiomaHabla, usuarioJoin.get("idiomas")));
        }
        if (idiomaEnseñaId != null) {
            Idioma idiomaEnseña = idiomaService.findById(idiomaEnseñaId);
            predicates.add(cb.isMember(idiomaEnseña, profesorRoot.get("idiomasEnseña")));
        }
        if (tipoId != null) {
            Clasetipo tipo = tipoClaseService.findById(tipoId);
            predicates.add(cb.isMember(tipo, profesorRoot.get("clasetipos")));
        }
        if (min != null) {
            predicates.add(cb.greaterThanOrEqualTo(profesorRoot.get("pricePerHour"), min));
        }
        if (max != null) {
            predicates.add(cb.lessThanOrEqualTo(profesorRoot.get("pricePerHour"), max));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq).getResultList();
    }
    public String getVideoPathByProfesorId(Integer profesorId) {
        Profesor profesor = profesorRepositorio.findById(profesorId).orElse(null);
        return profesor != null ? profesor.getVideoPath() : null;
    }

    public Set<Clase> getClasesByProfesorId(Integer profesorId) {
        Profesor profesor = profesorRepositorio.findById(profesorId).orElse(null);
        return profesor != null ? profesor.getClases() : null;
    }
}
