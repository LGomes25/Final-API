package org.serratec.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @Operation(summary = "Lista todos os produtos", description = "A resposta lista os dados dos produtos id, nome, preco, categoria.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Retorna todos os produtos"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @Operation(summary = "Busca um produto pelo ID", description = "A resposta retorna os detalhes de um produto específico, incluindo sua categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Cadastra um produto", description = "A resposta Cadastra um novo produto no sistema, associando-o a uma categoria.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Cadastrar cliente"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoSalvo = produtoService.inserir(produtoRequestDTO);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Edita um produto existente", description = "Atualiza os dados de um produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos, nome de produto já em uso ou categoria não encontrada"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(produtoService.editar(id, produtoRequestDTO));
    }

    @Operation(summary = "Remove um produto", description = "Remove um produto pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Não é possível remover o produto (ex: está em pedidos)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista produtos de forma paginada",
            description = "Retorna uma página de produtos. É possível controlar o tamanho da página, a página atual e a ordenação através dos parâmetros de query. " +
                    "Exemplo: /produtos/pagina?page=0&size=5&sort=nome,asc&sort=preco,desc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de produtos retornada com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro interno da aplicação")})

    @GetMapping("/pagina")
    public Page<ProdutoResponseDTO> listarPorPagina(@PageableDefault(page = 1, size = 10, sort = { "nome",
            "preco" }, direction = Sort.Direction.ASC) Pageable pageable) {
        return produtoService.listarPorPagina(pageable);
    }


    @Operation(summary = "Lista produtos por faixa de preço com paginação",
            description = "Retorna uma página de produtos cujo preço está entre os valores 'faixa1' (mínimo) e 'faixa2' (máximo)" +
                    "Exemplo: /produtos/faixa?faixa1=10.0&faixa2=50.0&page=0&size=5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de produtos na faixa de preço retornada com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos (ex: faixa1 > faixa2)"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})

    @GetMapping("/faixa")
    public Page<ProdutoResponseDTO> listarPorFaixaPreco(@RequestParam Double faixa1, @RequestParam Double faixa2,
                                                        Pageable pageable) {
        return produtoService.listarPorPaginaFaixaPreco(faixa1, faixa2, pageable);
    }

}