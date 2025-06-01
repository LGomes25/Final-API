package org.serratec.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório")
    private Double preco;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long idCategoria;

}