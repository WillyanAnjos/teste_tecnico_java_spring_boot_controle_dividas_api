package com.willyan.dividas.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String resource, Object id) {
        super(resource + " não encontrado(a) com identificador: " + id);
    }
}
