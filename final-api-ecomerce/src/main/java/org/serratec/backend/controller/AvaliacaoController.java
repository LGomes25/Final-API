package org.serratec.backend.controller;

import org.serratec.backend.dto.AvaliacaoRequestDTO;
import org.serratec.backend.dto.AvaliacaoResponseDTO;
import org.serratec.backend.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Lista todas as avaliações", description = "A resposta lista os dados de todas as avaliações de livros: id, note, idProduto, nomeProduto, idCliente e nomeCliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = AvaliacaoResponseDTO.class), mediaType = "application/json") }, description = "Retorna todas as avaliações"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarTodas(){
    	return ResponseEntity.ok(avaliacaoService.listarTodas());
    }
    
    @Operation(summary = "Lista todas as avaliações de um livro", description = "A resposta lista os dados de todas as avaliações de um determinado livro: id, note, idProduto, nomeProduto, idCliente e nomeCliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = AvaliacaoResponseDTO.class), mediaType = "application/json") }, description = "Retorna todas as avaliações"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(avaliacaoService.listarPorProduto(idProduto));
    }

    @Operation(summary = "Cria avaliação de um livro", description = "A resposta lista os dados da avaliação do livro inserida: id, note, idProduto, nomeProduto, idCliente e nomeCliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = AvaliacaoResponseDTO.class), mediaType = "application/json") }, description = "Retorna a avaliação criada"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> inserir(@Valid @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO) {
        return new ResponseEntity<>(avaliacaoService.inserir(avaliacaoRequestDTO), HttpStatus.CREATED);
    }
    
    @Operation(summary = "Atualiza a avaliação de um livro feita por um determinado cliente", description = "A resposta lista os dados atualizados da avaliação do livro: id, note, idProduto, nomeProduto, idCliente e nomeCliente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = AvaliacaoResponseDTO.class), mediaType = "application/json") }, description = "Retorna a atualização da avaliação"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping
    public ResponseEntity<AvaliacaoResponseDTO> atualizarPorProduto(@Valid @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO) {
        return ResponseEntity.ok(avaliacaoService.atualizarPorProduto(avaliacaoRequestDTO));
    }

    @Operation(summary = "Exclui uma avaliação pelo id", description = "Não apresenta mensagem de retorno")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Avaliação excluída com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        avaliacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}