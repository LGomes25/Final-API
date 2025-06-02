package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ListadeDesejosDTO;
import org.serratec.backend.entity.ListadeDesejos;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ListadeDesejosRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListadeDesejosService {

    @Autowired
    private ListadeDesejosRepository listadeDesejosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ListadeDesejosDTO criarListadeDesejos(ListadeDesejosDTO dto) {
        ListadeDesejos listadeDesejos = new ListadeDesejos();

        listadeDesejos.setNome(dto.getNome());
        listadeDesejos.setCliente(clienteRepository.findById(dto.getClienteId()).orElseThrow());

        List<Produto> produtos = new ArrayList<>();
        if (dto.getProdutoId() != null) {
            for (Long idProduto : dto.getProdutoId()) {
                produtos.add(produtoRepository.findById(idProduto).orElseThrow());
            }
        }
        listadeDesejos.setProdutos(produtos);
        ListadeDesejos salva = listadeDesejosRepository.save(listadeDesejos);

        // Retorna o DTO
        ListadeDesejosDTO response = new ListadeDesejosDTO();
        response.setId(salva.getId());
        response.setClienteId(salva.getCliente().getId());
        response.setNome(salva.getNome());
        response.setProdutoId(
                salva.getProdutos().stream().map(Produto::getId).toList()
        );
        return response;
    }

    public ListadeDesejosDTO buscarPorId(Long id) {
        ListadeDesejos l = listadeDesejosRepository.findById(id).orElseThrow();
        ListadeDesejosDTO dto = new ListadeDesejosDTO();
        dto.setId(l.getId());
        dto.setClienteId(l.getCliente().getId());
        dto.setNome(l.getNome());
        dto.setProdutoId(l.getProdutos().stream().map(Produto::getId).toList());
        return dto;
    }

    public List<ListadeDesejosDTO> listarPorCliente(Long clienteId) {
        return listadeDesejosRepository.findByClienteId(clienteId).stream().map(l -> {
            ListadeDesejosDTO dto = new ListadeDesejosDTO();
            dto.setId(l.getId());
            dto.setClienteId(l.getCliente().getId());
            dto.setNome(l.getNome());
            dto.setProdutoId(l.getProdutos().stream().map(Produto::getId).toList());
            return dto;
        }).toList();
    }

    public void adicionarProduto(Long listadeDesejosId, Long produtoId) {
        ListadeDesejos l = listadeDesejosRepository.findById(listadeDesejosId).orElseThrow();
        Produto p = produtoRepository.findById(produtoId).orElseThrow();
        l.getProdutos().add(p);
        listadeDesejosRepository.save(l);
    }

    public void removerProduto(Long listadeDesejosId, Long produtoId) {
        ListadeDesejos l = listadeDesejosRepository.findById(listadeDesejosId).orElseThrow();
        Produto p = produtoRepository.findById(produtoId).orElseThrow();
        l.getProdutos().remove(p);
        listadeDesejosRepository.save(l);
    }
}
