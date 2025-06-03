package org.serratec.backend.controller;

import org.serratec.backend.dto.ListadeDesejosRequestDTO;
import org.serratec.backend.dto.ListadeDesejosResponseDTO;
import org.serratec.backend.service.ListadeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listaDeDesejos")
public class ListadeDesejosController {

    @Autowired
    private ListadeDesejosService listadeDesejosService;

    @PostMapping
    public ResponseEntity<ListadeDesejosResponseDTO> criarListadeDesejos(
            @RequestBody ListadeDesejosRequestDTO requestDto) {
        ListadeDesejosResponseDTO response = listadeDesejosService.criarListadeDesejos(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadeDesejosResponseDTO> buscarPorId(@PathVariable Long id) {
        ListadeDesejosResponseDTO response = listadeDesejosService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ListadeDesejosResponseDTO>> listarPorCliente(
            @PathVariable Long clienteId) {
        List<ListadeDesejosResponseDTO> lista = listadeDesejosService.listarPorCliente(clienteId);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}/add/{produtoId}")
    public ResponseEntity<Void> adicionarProduto(@PathVariable Long id, @PathVariable Long produtoId) {
        listadeDesejosService.adicionarProduto(id, produtoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/remove/{produtoId}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id, @PathVariable Long produtoId) {
        listadeDesejosService.removerProduto(id, produtoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/share")
    public ResponseEntity<String> compartilhar(@PathVariable Long id) {
        String url = "https://sebooline.com/listadesejos/" + id;
        return ResponseEntity.ok(url);
    }
}
