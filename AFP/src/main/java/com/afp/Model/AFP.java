package com.afp.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 *Clase Afp, sirve para la vinculacion con el cliente
 *parametros 
 *	String=numberAccount: nro de cuenta
 *	float=cash: monto disponible
 *	float=retire: monto a retarar
 *	String=typeAccount: Nombre de la AFP
 *	LocalDateTime=dateNow: fecha de retiro
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "afp")
public class AFP {

    @Id
    private String numberAccount;
    @Column(name = "cash", length = 32, unique = false, nullable = false)
    private float cash;
    @Column(name = "retire", length = 32, unique = false, nullable = false)
    private float retire;
    @Column(name = "typeAccount", length = 32, unique = false, nullable = false)
    private String typeAccount;   
    @JsonFormat(pattern = "dd::MM::yyyy KK:mm a")
    @Column(name = "dateNow", unique = false, nullable = true)
    private LocalDateTime dateNow;

}
