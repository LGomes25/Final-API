package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListadeDesejosResponseDTO {
    private Long id;
    private Long idCliente;
    private String nomeLista;
    private List<String> livros;
}
