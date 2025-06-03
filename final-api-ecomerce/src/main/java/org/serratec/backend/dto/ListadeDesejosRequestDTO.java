package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListadeDesejosRequestDTO {
    private Long clienteId;
    private String nome;
    private List<Long> produtoId;
}
