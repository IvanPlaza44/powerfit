package com.uade.tpo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Producto no encontrado")
public class ProductNotFoundException extends Exception {

}