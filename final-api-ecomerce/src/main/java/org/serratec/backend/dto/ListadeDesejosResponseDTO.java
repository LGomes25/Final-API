package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListadeDesejosResponseDTO {
    private Long id;
    private String nome;
    private List<String> nomesprodutos;
}
