package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.PedidoProduto;

import java.util.List;

@Getter
@Setter

public class PedidoRequestDTO extends Pedido {
     private Long id_cliente;
     private List<PedidoProdutoDTO> produtos;
     private String status;


     public PedidoProduto[] getProdutosPedidos() {
     }

     public Thread getCliente() {
     }
}
