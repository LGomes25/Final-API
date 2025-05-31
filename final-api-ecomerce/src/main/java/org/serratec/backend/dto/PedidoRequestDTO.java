package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class PedidoRequestDTO {
     private Long clienteId;
     private List<PedidoProdutoDTO> produtos;
     private String status; 

      
}
