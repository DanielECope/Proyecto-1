package com.afp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afp.Model.Client;

// Creamos una interfaz y extendemos de jpa los querys
// No le ponemos la anotacion @Respoitory porque el servicio ya sobreentiende cuando extendemos JPA
public interface IClientRepository extends JpaRepository<Client, String> {

}
