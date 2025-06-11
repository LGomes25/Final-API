package org.serratec.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioPerfilResponseDTO{

	private Long id;
	private String nome;
	private List<String> perfis;
	
}

