package org.serratec.backend.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public PedidoResponseDTO criar(@RequestBody PedidoRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public PedidoResponseDTO alterarStatus(@PathVariable Long id, @RequestParam String status) {
        return service.alterarStatus(id, status);
    }

    @GetMapping("/{id}/total")
    public Double total(@PathVariable Long id) {
        return service.totalizarPedido(id);
    }
}

