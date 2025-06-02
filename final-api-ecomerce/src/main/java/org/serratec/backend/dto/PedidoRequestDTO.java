package org.serratec.backend.dto;

import java.util.List;

import org.serratec.backend.entity.Pedido;

import lombok.Getter;
import lombok.Setter;

@Setter

public class PedidoRequestDTO extends Pedido {
     private Long id_cliente;
     private List<PedidoProdutoDTO> produtos;
     private String status;


//     public PedidoProduto[] getProdutosPedidos() {
//     }
//
//     public Thread getCliente() {
//     }
     
}
