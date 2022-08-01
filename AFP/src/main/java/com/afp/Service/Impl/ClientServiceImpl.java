package com.afp.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afp.Exception.ModelNotFoundException;
import com.afp.Model.AFP;
import com.afp.Model.Client;

import com.afp.Repository.IAfpRepository;
import com.afp.Repository.IClientRepository;
import com.afp.Service.IClientService;



/*
 * Clase (servicio)  ClientServiceImpl, es para sobreescribir los metodos de la interfaz
 * metodo findAll devuelve todos los datos de la tabla cliente
 * metodo create: guarda los datos en la tabla
 * metodo constructorUser: setea el obj de tipo cliente
 */
@Service
public class ClientServiceImpl implements IClientService {
	private static final Logger Logger=LoggerFactory.getLogger(ClientServiceImpl.class);
    
    @Autowired
    IClientRepository clientRepository;
    @Autowired
    IAfpRepository afpRepository;

   
    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client create(Client request) {
        if (!existUser(request.getDni())) {
            try {
                if (validUser(request)) {
                    Client user = constructorUser(request);
                    this.afpRepository.save(user.getAfp());
                    return this.clientRepository.save(user);
                } 
            } catch (Exception e) {
            	Logger.info("Ocurrio un error: "+e.getMessage());
                throw e;
            }
        }
        throw new ModelNotFoundException("DNI ya esta registrado");
    }

    private Client constructorUser(Client request) {
        AFP afp = AFP.builder()
                            .numberAccount(request.getName().charAt(0) + request.getDni())
                            .cash(request.getAfp().getCash())
                            .typeAccount(request.getAfp().getTypeAccount())
                            .retire(0)
                            .dateNow(null)
                            .build();

        Client user = Client.builder()
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .afp(afp)
                .build();
        return user;
    }

    private boolean validUser(Client request) {
        if (request.getDni().length() == 8) {
            if (request.getPhone().length() == 9) {
                if (validTypeAccount(request)) {
                    return true;
                }
                throw new ModelNotFoundException("Tipo de cuenta invalido");
            }
            throw new ModelNotFoundException("El numero telefonico debe tener 9 numeros");
        }
        throw new ModelNotFoundException("El DNI debe tener 8 numeros");
    }

    private Boolean validTypeAccount(Client request) {
        List<String> types=new ArrayList<>(Arrays.asList("INTEGRA", "PRIMA", "HABITAT", "PROFUTURO")); 
        return types.contains(request.getAfp().getTypeAccount());
    }

    private boolean existUser(String id) {
        List<Client> list = this.clientRepository.findAll().stream()
                            .filter(client -> client.getDni().equals(id))
                            .collect(Collectors.toList());
        if (list.isEmpty()) return false;
        return true;
    }

    @Override
    public Client update(Client request, String id) {
        return this.clientRepository.save(request);
    }

    @Override
    public Client delete(String id) {
        this.clientRepository.deleteById(id);
        throw new ModelNotFoundException("El cliente se elimino");
    }
    
}
