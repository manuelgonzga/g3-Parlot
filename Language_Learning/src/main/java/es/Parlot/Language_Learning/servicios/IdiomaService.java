package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Idioma;
import es.Parlot.Language_Learning.repositorios.IdiomaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {

    @Autowired
    IdiomaRepositorio idiomaRepositorio;

    public void save(Idioma idioma){
        idiomaRepositorio.saveAndFlush(idioma);
    }

    public Idioma findById(Integer id){
        return idiomaRepositorio.findById(id).get();
    }

    public List<Idioma> getAll(){
        return idiomaRepositorio.findAll();
    }

}
