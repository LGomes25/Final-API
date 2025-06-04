package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.FuncionarioRequestDTO;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@Operation(summary = "Lista todos os funcionarios", description = "A resposta lista os dados de todos os funcionarios: id, nome, telefone e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = FuncionarioResponseDTO.class), mediaType = "application/json") }, description = "Retorna todos os funcionarios"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<FuncionarioResponseDTO>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@Operation(summary = "Cadastra um funcionarios", description = "A resposta lista os dados do funcionario inserido: id, nome, telefone e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = FuncionarioResponseDTO.class), mediaType = "application/json") }, description = "Cadastra um funcionario"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FuncionarioResponseDTO inserir(@Valid @RequestBody FuncionarioRequestDTO funcionario) {
		return service.inserir(funcionario);
	}
	
	@Operation(summary = "Atualiza um funcionario pelo id", description = "A resposta lista os dados do funcionario atualizado: id, nome, telefone e email.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = FuncionarioResponseDTO.class), mediaType = "application/json") }, description = "Retorna funcionario atualizado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PutMapping ("{id}")
	public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioRequestDTO funcionario){ 
		return ResponseEntity.ok(service.atualizar(id, funcionario));
	}
	
	@Operation(summary = "Exclui um funcionario pelo id", description = "Não apresenta mensagem de retorno")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Funcionario excluído com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	
}