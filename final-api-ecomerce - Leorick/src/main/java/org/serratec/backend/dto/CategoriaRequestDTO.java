package org.serratec.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaRequestDTO {

	@NotBlank(message = "O nome é obrigatório.")
	@Column(nullable = false)
	private String nome;
	
}
