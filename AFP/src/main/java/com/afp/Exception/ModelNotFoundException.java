package com.afp.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ModelNotFoundException extends RuntimeException {
    
    public ModelNotFoundException(String mensaje){
        super(mensaje);
    }
    
}
