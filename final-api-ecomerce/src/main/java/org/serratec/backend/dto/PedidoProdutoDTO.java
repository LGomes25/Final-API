package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoProdutoDTO {

    private Long id_produto;
    private Integer quantidade;
    private Double valorVenda;
    private Double desconto;

}

