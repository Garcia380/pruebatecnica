package com.alfredogarcia.evaluacion.service;

import com.alfredogarcia.evaluacion.dto.*;
import com.alfredogarcia.evaluacion.entity.Transaccion;
import com.alfredogarcia.evaluacion.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    public TransaccionResponse guardarTransaccion(TransaccionRequest req) {
        Transaccion entity = new Transaccion(); 
        entity.setOperacion(req.operacion());
        entity.setImporte(req.importe());
        entity.setCliente(req.cliente());
        entity.setSecreto(req.firma());
        
        entity.setEstatus("Aprobada");
        entity.setReferencia(String.valueOf(100000 + new java.util.Random().nextInt(900000)));

        Transaccion t = repository.save(entity);
        return new TransaccionResponse(t.getId(), t.getEstatus(), t.getReferencia(), t.getOperacion());
    }
    public boolean cancelarTransaccion(TransaccionUpdate update) {
        // El requerimiento dice que el valor del estatus enviado será "cancelar"
        // pero en BD lo guardaremos como "cancelada" según la lógica típica
        String nuevoEstatus = update.estatus().equalsIgnoreCase("cancelar") ? "cancelada" : update.estatus();
        return repository.actualizarEstatus(update.id(), update.referencia(), nuevoEstatus) > 0;
    }

    public Optional<Transaccion> obtenerPorReferencia(String ref) {
        return repository.buscarPorReferencia(ref);
    }

    public List<Transaccion> obtenerTodas() {
        return repository.findAll();
    }
}