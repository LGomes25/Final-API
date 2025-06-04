package org.serratec.backend.controller;

import jakarta.validation.Valid;

import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.enums.StatusPedido;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Cadastra um Pedido", description = "A resposta lista os dados do pedido inserido: id, status, dataPedido, idcliente, lista de produtos e total.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Cadastra um pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    public PedidoResponseDTO criar(@RequestBody @Valid PedidoRequestDTO dto) {
        return pedidoService.criarPedido(dto);
    }

    @Operation(summary = "Lista um pedido pelo id", description = "A resposta lista os dados de um pedido por id: id, status, dataPedido, idcliente, lista de produtos e total.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna um pedido especifico"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @Operation(summary = "Lista todos os pedidos", description = "A resposta lista os dados de todos os pedidos: id, status, dataPedido, idcliente, lista de produtos e total.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna todos os pedidos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return pedidoService.listarPedidos();
    }

    @Operation(summary = "Exclui um pedido pelo id", description = "Não apresenta mensagem de retorno")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }

    @Operation(summary = "Atualiza um pedido pelo id", description = "A resposta lista os dados do pedido atualizado: id, status, dataPedido, idcliente, lista de produtos e total.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }, description = "Retorna um pedido atualizado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping("/{id}/status")
    public PedidoResponseDTO alterarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return pedidoService.alterarStatus(id, status);
    }

}
