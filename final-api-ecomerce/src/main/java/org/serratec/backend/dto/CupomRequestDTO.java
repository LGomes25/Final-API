package org.serratec.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CupomRequestDTO {

    @NotBlank(message = "O código do cupom não pode ser vazio")
    @Size(max = 10, message = "O código deve ter no máximo 10 caracteres")
    private String codigo;

    @NotNull(message = "O valor de desconto não pode ser nulo")
    @DecimalMin(value = "0.00", inclusive = false, message = "O desconto deve ser maior que 0")
    private BigDecimal valorDesconto;

    @NotNull(message = "A data de validade não pode ser nula")
    @FutureOrPresent(message = "A data de validade deve ser hoje ou futura")
    private LocalDate validadeData;

    private Boolean usado;

	public CupomRequestDTO(BigDecimal valorDesconto,LocalDate validadeData) {
		this.valorDesconto = valorDesconto;
		this.validadeData = validadeData;
	}
    
    

}
