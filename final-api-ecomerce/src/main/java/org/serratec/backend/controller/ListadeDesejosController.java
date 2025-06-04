package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.ListadeDesejosRequestDTO;
import org.serratec.backend.dto.ListadeDesejosResponseDTO;
import org.serratec.backend.service.ListadeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<ListadeDesejosResponseDTO> buscarPorId(@PathVariable Long id) {
//        ListadeDesejosResponseDTO response = listadeDesejosService.buscarPorId(id);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/cliente/{clienteId}")
//    public ResponseEntity<List<ListadeDesejosResponseDTO>> listarPorCliente(
//            @PathVariable Long clienteId) {
//        List<ListadeDesejosResponseDTO> lista = listadeDesejosService.listarPorCliente(clienteId);
//        return ResponseEntity.ok(lista);
//    }
//
//    @PutMapping("/{id}/add/{produtoId}")
//    public ResponseEntity<Void> adicionarProduto(@PathVariable Long id, @PathVariable Long produtoId) {
//        listadeDesejosService.adicionarProduto(id, produtoId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{id}/remove/{produtoId}")
//    public ResponseEntity<Void> removerProduto(@PathVariable Long id, @PathVariable Long produtoId) {
//        listadeDesejosService.removerProduto(id, produtoId);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{id}/share")
//    public ResponseEntity<String> compartilhar(@PathVariable Long id) {
//        String url = "https://sebooline.com/listadesejos/" + id;
//        return ResponseEntity.ok(url);
//    }
}
