package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Long CategoriaId;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.CategoriaId = produto.getId();
        }
    }
