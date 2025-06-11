package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.ListadeDesejosRequestDTO;
import org.serratec.backend.dto.ListadeDesejosResponseDTO;
import org.serratec.backend.service.ListadeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/listaDeDesejos")
public class ListadeDesejosController {

    @Autowired
    private ListadeDesejosService listadeDesejosService;
    
    @Operation(summary = "Lista todas as listas de Desejos dos Clientes", description = "A resposta apresenta todas as lista de livros desejados pelos clientes: idCliente, nomeLista, List<Strind>livros.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna todas as Lista de desejos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
	public ResponseEntity<List<ListadeDesejosResponseDTO>> listarTodasListas(){
		return ResponseEntity.ok(listadeDesejosService.listarTodas());
	}

    @Operation(summary = "Lista de Desejos de Cliente", description = "A resposta apresenta a criação de uma lista de livros desejado pelo cliente: idCliente, nomeLista, List<Strind>livros.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna Lista de desejos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    public ResponseEntity<ListadeDesejosResponseDTO> criarListadeDesejos(
            @RequestBody ListadeDesejosRequestDTO requestDto) {
        ListadeDesejosResponseDTO response = listadeDesejosService.criarListadeDesejos(requestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista de Desejos dos Clientes por Id da Lista", description = "A resposta apresenta lista de livros desejados pelos clientes buscada por id: idCliente, nomeLista, List<Strind>livros.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna Lista de desejos por id"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/{id}")
    public ResponseEntity<ListadeDesejosResponseDTO> buscarPorId(@PathVariable Long id) {
        ListadeDesejosResponseDTO response = listadeDesejosService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista de Desejos do Cliente por Id do Cliente", description = "A resposta apresenta listas de livros desejados pelo cliente buscada por id do cliente: idCliente, nomeLista, List<Strind>livros.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna Lista de desejos do cliente por id do cliente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ListadeDesejosResponseDTO>> listarPorCliente(@PathVariable Long clienteId) {
        List<ListadeDesejosResponseDTO> lista = listadeDesejosService.listarPorCliente(clienteId);
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Exclui uma lista de desejos pelo id", description = "Não apresenta mensagem de retorno")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Lista de Desejo excluída com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
    	listadeDesejosService.removerLista(id);
	}
}
