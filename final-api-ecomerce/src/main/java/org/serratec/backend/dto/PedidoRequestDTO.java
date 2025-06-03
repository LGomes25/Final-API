package org.serratec.backend.dto;

import java.util.List;

import org.serratec.backend.enums.StatusPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PedidoRequestDTO {
     private StatusPedido status;
     private Long idCliente;
     private List<PedidoProdutoDTO> produtos;
	
     public PedidoRequestDTO(Long idCliente, List<PedidoProdutoDTO> produtos) {
		this.idCliente = idCliente;
		this.produtos = produtos;
	}

	public PedidoRequestDTO(StatusPedido status, Long idCliente, List<PedidoProdutoDTO> produtos) {
		this.status = status;
		this.idCliente = idCliente;
		this.produtos = produtos;
	}
     
}


