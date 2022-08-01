package com.afp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afp.Model.AFP;
import com.afp.Service.IAfpService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * Clase AfpController, se utiliza para registrar la solicitud y obtener los datos por solicitud
 */
@Slf4j
@RestController
@RequestMapping("api/v1/afp")
@AllArgsConstructor
public class AfpController {

    @Autowired
    IAfpService afpService;

    @GetMapping(path = "/get", params = "id")
    public AFP getById(@RequestParam String id) {
        log.info("Se retorno retiro");
        return this.afpService.get(id);
    }

    @PostMapping(path = "/create")
    public AFP create(@RequestParam Float retiro, @RequestParam String id) {
        log.info("Nuevo registro: {}", id);
        return this.afpService.create(id, retiro);
    }
    
}
