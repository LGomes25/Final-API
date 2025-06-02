package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public PedidoResponseDTO criar(@RequestBody @Valid PedidoRequestDTO dto) {
        return pedidoService.criarPedido(dto);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return pedidoService.listarPedidos();
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO dto) {
        return pedidoService.atualizarPedido(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }

    @PatchMapping("/{id}/status")
    public PedidoResponseDTO alterarStatus(@PathVariable Long id, @RequestParam String status) {
        return pedidoService.alterarStatus(id, status);
    }

    @GetMapping("/{id}/total")
    public Double totalizar(@PathVariable Long id) {
        return pedidoService.totalizarPedido(id);
    }
}
