package org.serratec.backend.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {

      super("Cliente com ID "+ id + "nãoencontrado");
    }
}
