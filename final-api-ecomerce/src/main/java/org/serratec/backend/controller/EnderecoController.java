package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping ("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@Operation(summary = "Busca um endereço pelo CEP no BD ou no ViaCep", description = "A resposta lista o endereço referente ao CEP informado. Caso não exista no BD é acessada a API o ViaCep e o endereço é acessado e gravado no BD")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna Endereço completo referente ao CEP"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("{cep}")
	public ResponseEntity<EnderecoResponseDTO> buscarCEP(@PathVariable String cep){
		var end = service.buscar(cep);
		
		if(end != null) {
			return ResponseEntity.ok(end);
		}
		return ResponseEntity.notFound().build();
	}
	
}
