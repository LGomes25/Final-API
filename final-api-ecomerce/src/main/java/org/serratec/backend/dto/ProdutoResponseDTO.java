package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;

public record ProdutoResponseDTO(Long id, String nome, Double preco) {

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        if (produto.getCategoria() != null) {
            //ele vai apontar erro aqui até que a Categoria Response esteja criada
           this.categoria = new CategoriaResponseDTO(produto.getCategoria());
        }
    }

}