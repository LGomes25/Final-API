package org.serratec.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.backend.entity.Pedido;
import org.serratec.backend.enums.StatusPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private StatusPedido status;
    private LocalDateTime dataPedido;
    private Long cliente;
    private List<PedidoProdutoDTO> produtos;
    private Double total;
	
    public PedidoResponseDTO(Long id, StatusPedido status, LocalDateTime dataPedido, Long cliente, List<PedidoProdutoDTO> produtos, Double total) {
		this.id = id;
		this.status = status;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
		this.produtos = produtos;
		this.total = total;
	}
    //Inserir dados 
    public PedidoResponseDTO(Pedido pedido) {
    	this.id = pedido.getId();
		this.status = pedido.getStatus();
		this.dataPedido = pedido.getDataPedido();
		this.cliente = pedido.getCliente().getId();

	}
    
}
