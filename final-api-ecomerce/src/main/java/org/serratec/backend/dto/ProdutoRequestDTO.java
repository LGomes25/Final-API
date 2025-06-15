package org.serratec.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do livro é obrigatório")
    private String nome;

    @NotNull(message = "O preço do livro é obrigatório")
    private Double preco;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;
    
    @NotBlank(message = "O ISBN do livro é obrigatório")
    private String isbn;

}