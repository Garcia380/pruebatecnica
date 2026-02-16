package com.alfredogarcia.evaluacion.client;

import com.alfredogarcia.evaluacion.dto.TransaccionRequest;
import com.alfredogarcia.evaluacion.dto.TransaccionResponse; 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-persistencia", url = "http://localhost:8081/api/persistencia")
public interface PersistenciaClient {

    @PostMapping("/guardar")
    TransaccionResponse guardarEnPersistencia(@RequestBody TransaccionRequest request);
}