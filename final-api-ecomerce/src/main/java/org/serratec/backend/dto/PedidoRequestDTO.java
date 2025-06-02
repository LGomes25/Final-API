package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {
     private String status;
     private Long idCliente;
     private List<PedidoProdutoDTO> produtos;
}


