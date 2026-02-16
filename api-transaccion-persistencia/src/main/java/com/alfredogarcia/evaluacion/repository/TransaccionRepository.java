package com.alfredogarcia.evaluacion.repository;

import com.alfredogarcia.evaluacion.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    // Requerimiento: Consulta utilizando @Query por referencia
    @Query("SELECT t FROM Transaccion t WHERE t.referencia = :referencia")
    Optional<Transaccion> buscarPorReferencia(String referencia);

    //Actualizar estatus con @Query (PATCH)
    @Transactional
    @Modifying
    @Query("UPDATE Transaccion t SET t.estatus = :estatus WHERE t.id = :id AND t.referencia = :referencia")
    int actualizarEstatus(Long id, String referencia, String estatus);
}