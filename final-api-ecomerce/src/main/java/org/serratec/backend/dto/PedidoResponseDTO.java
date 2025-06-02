package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PedidoResponseDTO {

    private Long id;
    private String status;
    private LocalDate data;
    private ClienteResponseDTO cliente;
    private List<PedidoProdutoDTO> produtos;
    private Double total;

}
