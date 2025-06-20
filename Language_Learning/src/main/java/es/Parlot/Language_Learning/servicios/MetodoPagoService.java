package es.Parlot.Language_Learning.servicios;


import es.Parlot.Language_Learning.modelo.MetodoDePago;
import es.Parlot.Language_Learning.repositorios.MetodoPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoPagoService {

    @Autowired
    MetodoPagoRepositorio metodoPagoRepositorio;

    public void save(MetodoDePago metodoDePago){
        metodoPagoRepositorio.saveAndFlush(metodoDePago);
    }

    public MetodoDePago getMetodoPagoById(Integer id){
        return metodoPagoRepositorio.findById(id).orElse(null);
    }
}
