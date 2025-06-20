package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Pais;
import es.Parlot.Language_Learning.repositorios.PaisRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaisService {

    @Autowired
    PaisRepositorio paisRepositorio;

    public List<Pais> getAll() {
        return paisRepositorio.findAll();
    }

}
