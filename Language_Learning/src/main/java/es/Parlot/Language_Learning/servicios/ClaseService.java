package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Clase;
import es.Parlot.Language_Learning.modelo.Idioma;
import es.Parlot.Language_Learning.repositorios.ClaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseService {

    @Autowired
    ClaseRepositorio claseRepositorio;

    @Autowired
    IdiomaService idiomaService;

    public Idioma getIdiomabyId(Integer id){
        return idiomaService.findById(id);
    }

    public Clase getClaseById(Integer id){
        return claseRepositorio.findById(id).get();
    }

    public void save(Clase clase){
        claseRepositorio.saveAndFlush(clase);
    }

    public void saveAll(Iterable<Clase> clases){
        claseRepositorio.saveAll(clases);
    }

    public void delete(Integer id) {
        claseRepositorio.deleteById(id);
    }
}
