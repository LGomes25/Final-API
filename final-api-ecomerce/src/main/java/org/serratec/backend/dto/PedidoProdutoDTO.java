package org.serratec.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoDTO {

    private Long idProduto;
    private Integer quantidade;
    private Double valorVenda;
    private Double desconto;

}

