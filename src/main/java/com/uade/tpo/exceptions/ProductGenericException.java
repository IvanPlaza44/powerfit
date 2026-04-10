package com.uade.tpo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Esta accion no se ha podido completar, vuelva a intentarlo mas tarde")
public class ProductGenericException extends RuntimeException{
    
}
