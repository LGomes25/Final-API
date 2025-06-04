package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.IsbnResponseDTO;
import org.serratec.backend.service.IsbnService;
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
@RequestMapping("/isbn")
public class IsbnController {

	@Autowired
    private IsbnService service;
	
	@Operation(summary = "Busca informações de um livro pelo ISBN no BD ou no OpenLibrary", description = "A resposta lista os dados do livro referente ao ISBN informado. Caso não exista no BD é acessada a API OpenLibrary e o livro é acessado e gravado no BD")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna os dados do livro: isbn, titulo, descricao, numeroPaginas, dataPublicacao, editoras, autores e urlCapa"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/{isbn}")
	public ResponseEntity<IsbnResponseDTO> buscarLivro(@PathVariable String isbn){
		var livro = service.buscarLivroPorIsbn(isbn);
		
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}
		return ResponseEntity.notFound().build();
	}
    
}
