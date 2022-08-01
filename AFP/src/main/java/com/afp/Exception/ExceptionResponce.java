package com.afp.Exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponce {

    @JsonFormat(pattern = "dd::MM::yyyy KK:mm a")
    private LocalDateTime fecha;
    private String mensaje;
    private String detalle;
    
}
