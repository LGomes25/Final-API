package org.serratec.backend.controller;

import org.serratec.backend.dto.AvaliacaoRequestDTO;
import org.serratec.backend.dto.AvaliacaoResponseDTO;
import org.serratec.backend.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(avaliacaoService.listarPorProduto(idProduto));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> inserir(@Valid @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO) {
        return new ResponseEntity<>(avaliacaoService.inserir(avaliacaoRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        avaliacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}