package org.serratec.backend.dto;

import org.serratec.backend.entity.Avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {

	
    @NotNull(message = "O ID do produto é obrigatório")
    private Long idProduto;

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long idCliente;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;

	public AvaliacaoRequestDTO(Avaliacao avaliacao) {
		this.nota = avaliacao.getNota();
		this.idProduto = avaliacao.getProduto().getId();
		this.idCliente = avaliacao.getCliente().getId();
	}
    
    
    
    
    
    
}