package com.afp.Service.Impl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afp.Exception.ModelNotFoundException;
import com.afp.Model.AFP;
import com.afp.Repository.IAfpRepository;
import com.afp.Service.IAfpService;

/*
 * la clase  AfpServiceImpl, se utiliza para sobreescribir los metodos del interfaz
 * metodos:
 * 		get
 * 		create
 * 		existAFP
 * 		validAfp
 */

@Service
public class AfpServiceImpl implements IAfpService {
	private static final Logger Logger=LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    IAfpRepository afpRepository;

    @Override
    public AFP get(String id) {
        AFP afp = this.afpRepository.findAll().stream()
        .filter(request -> request.getNumberAccount().equals(id))
        .collect(Collectors.toList()).get(0);
        if (afp.equals(null)) {
            throw new ModelNotFoundException("La cuenta afp no existe");
        } else {
            return afp;
        }
    }

    @Override
    public AFP create(String id, float retiro) {
        if (existAFP(id)) {
            try {
                if (validAfp(id, retiro)) {
                    AFP afp = this.afpRepository.getOne(id);
                    afp.setRetire(retiro);
                    String date = "2023-12-03T10:15:30";
                    afp.setDateNow(LocalDateTime.parse(date));
                    return this.afpRepository.save(afp);
                }
            } catch (Exception e) {
            	Logger.info("Ocurrio un error: "+e.getMessage());
                throw e;
            }
        }
        throw new ModelNotFoundException("El afp no existe");
    }
    private boolean existAFP(String id) {
        return this.afpRepository.existsById(id);
    }

    private boolean validAfp(String id, float retiro) {
        AFP afp = this.afpRepository.getOne(id);
        if (afp.getCash() /2 < retiro) {
            return true;
        }
        throw new ModelNotFoundException("Monto no permitido; no cumple con lo establecido");
    }
    
}
