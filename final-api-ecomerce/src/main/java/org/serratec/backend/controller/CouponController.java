package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.CupomRequestDTO;
import org.serratec.backend.dto.CupomResponseDTO;
import org.serratec.backend.service.CupomService;
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
@RequestMapping("/cupons")
public class CouponController {

	@Autowired
    private CupomService cupomService;

	@Operation(summary = "Cadastra um cupom", description = "A resposta lista os dados do cupom cadastrado: codigo, validade, valor e status.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = CupomResponseDTO.class), mediaType = "application/json") }, description = "Cadastra um cupom"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CupomResponseDTO criarCupom(@Valid @RequestBody CupomRequestDTO dto) {
        return cupomService.criarCupom(dto); 
    }

	@Operation(summary = "Lista todos os cupons cadastrados", description = "A resposta lista todos os cupons cadastrados: codigo, validade, valor e status.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = CupomResponseDTO.class), mediaType = "application/json") }, description = "Lista todos os cupom"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public ResponseEntity<List<CupomResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cupomService.listarTodos());
    }

	@Operation(summary = "Lista cupom pelo código", description = "A resposta apresenta o cupom pelo codigo cadatrado: codigo, validade, valor e status.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = CupomResponseDTO.class), mediaType = "application/json") }, description = "Apresenta cupom pelo código"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CupomResponseDTO> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(cupomService.listarPorCodigo(codigo));
    }

	@Operation(summary = "Atualiza um cupom pelo codigo", description = "A resposta lista os dados do cupom atualizado pelo codigo: codigo, validade, valor e status.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = CupomResponseDTO.class), mediaType = "application/json") }, description = "Atualiza um cupom"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping("/codigo/{codigo}")
    public ResponseEntity<CupomResponseDTO> atualizar(@PathVariable String codigo,@Valid @RequestBody CupomRequestDTO dto) {
        return ResponseEntity.ok(cupomService.atualizar(codigo, dto));
    }
	@Operation(summary = "Exclui um cupom pelo código", description = "Não apresenta mensagem de retorno")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Cupom excluído com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable String codigo) {
        cupomService.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
