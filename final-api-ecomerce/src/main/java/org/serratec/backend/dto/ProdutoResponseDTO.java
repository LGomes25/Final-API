package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Long categoriaId;
    private String isbn;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.isbn = produto.getIsbn();
        this.categoriaId = produto.getCategoria().getId();
        }
    }
