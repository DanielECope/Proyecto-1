package com.afp.Service;

import com.afp.Model.AFP;
//se crea la interfaz de IAFP para definir los metodos a implementar
public interface IAfpService {

    AFP get(String id);
    AFP create(String id, float retiro);
    
}
