package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PedidoProdutoDTO {

    private String nome;
    private Integer quantidade;
    private Double precoVenda;
    private Integer id_produto;

}

