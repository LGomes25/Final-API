package org.serratec.backend.controller;

import org.serratec.backend.dto.ListadeDesejosDTO;
import org.serratec.backend.service.ListadeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listaDeDesejos")
public class ListadeDesejosController {

        @Autowired
        private ListadeDesejosService listadeDesejosService;

        @PostMapping
        public ListadeDesejosDTO criarListadeDesejos(@RequestBody ListadeDesejosDTO dto) {
            return listadeDesejosService.criarListadeDesejos(dto);
        }

        @GetMapping("/{id}")
        public ListadeDesejosDTO buscarPorId(@PathVariable Long id) {
            return listadeDesejosService.buscarPorId(id);
        }

        @GetMapping("/cliente/{clienteId}")
        public List<ListadeDesejosDTO> listarPorCliente(@PathVariable Long clienteId) {
            return listadeDesejosService.listarPorCliente(clienteId);
        }

        @PutMapping("/{id}/add/{produtoId}")
        public void adicionarProduto(@PathVariable Long id, @PathVariable Long produtoId) {
            listadeDesejosService.adicionarProduto(id, produtoId);
        }

        @PutMapping("/{id}/remove/{produtoId}")
        public void removerProduto(@PathVariable Long id, @PathVariable Long produtoId) {
            listadeDesejosService.removerProduto(id, produtoId);
        }

        // Endpoint para compartilhar a lista
        @GetMapping("/{id}/share")
        public String compartilhar(@PathVariable Long id) {
            return "https://sebooline.com/listadesejos/" + id;
        }
    }


