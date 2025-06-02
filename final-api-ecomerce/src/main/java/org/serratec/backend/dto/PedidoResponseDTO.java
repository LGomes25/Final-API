package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoResponseDTO {

    private Long id;
    private String status;
    private LocalDateTime dataPedido;
    private Long cliente;
    private List<PedidoProdutoDTO> produtos;
    private Double total;

}
