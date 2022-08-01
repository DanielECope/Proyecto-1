package com.afp.Service;

import java.util.List;

import com.afp.Model.Client;

// Se crea la interfaz IClientService para definir los metodos a implementar
public interface IClientService {
    
    List<Client> findAll();
    Client create(Client request);
    Client update(Client request, String id);
    Client delete(String id);

}
