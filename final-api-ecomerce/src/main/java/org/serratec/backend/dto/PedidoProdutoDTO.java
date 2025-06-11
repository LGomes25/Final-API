package org.serratec.backend.dto;

//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class PedidoProdutoDTO {

    private Long idProduto;
    private Integer quantidade;
    private Double valorVenda;
    private Double desconto;
    private Double subTotal;

}

