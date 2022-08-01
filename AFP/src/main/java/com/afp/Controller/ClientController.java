package com.afp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afp.Model.Client;
import com.afp.Service.IClientService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * Clase ClientController expone los servicios
 */
@Slf4j
@RestController
@RequestMapping("api/v1/client")
@AllArgsConstructor
public class ClientController {

    @Autowired
    IClientService clientService;

    @GetMapping(path = "/findall")
    public List<Client> findAll() {
        log.info("Se retorno un finAll");
        return this.clientService.findAll();
    }

    @PostMapping("/create")
    public Client create(@RequestBody Client request) {
        log.info("Nuevo registro: {}", request);
        return this.clientService.create(request);
    }

    @PostMapping(path =  "/update", params = "id")
    public Client update(@RequestParam String id, @RequestBody Client request) {
        log.info("Actualizo registro: {}", request);
        return this.clientService.update(request, id);
    }

    @DeleteMapping(path = "/delete", params = "id")
    public Client delete(@RequestParam String id) {
        log.info("Elimino registro: {}", id);
        this.clientService.delete(id);
        return null;
    }
    
}
