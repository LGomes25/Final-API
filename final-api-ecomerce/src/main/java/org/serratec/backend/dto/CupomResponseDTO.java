package org.serratec.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomResponseDTO(
		String codigo, 
		LocalDate validade, 
		BigDecimal Valor, 
		String status) {

}
