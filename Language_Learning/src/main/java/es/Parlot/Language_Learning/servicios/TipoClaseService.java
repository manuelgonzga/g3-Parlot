package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Clasetipo;
import es.Parlot.Language_Learning.repositorios.TipoClaseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoClaseService {

    @Autowired
    TipoClaseRepositorio tipoClaseRepositorio;

    public List<Clasetipo> getAll(){
        return tipoClaseRepositorio.findAll();
    }

    public Clasetipo findById(Integer id){
        return tipoClaseRepositorio.findById(id).get();
    }

}
