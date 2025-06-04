package org.serratec.backend.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s não encontrado com %s = '%s'", resourceName, fieldName, fieldValue));
  }
}
