package org.serratec.backend.service;

import org.serratec.backend.dto.ListadeDesejosRequestDTO;
import org.serratec.backend.dto.ListadeDesejosResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ListadeDesejos;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ListadeDesejosRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListadeDesejosService {

    @Autowired
    private ListadeDesejosRepository listadeDesejosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;



    // ------------------ criar ------------------
    public ListadeDesejosResponseDTO criarListadeDesejos(ListadeDesejosRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<Produto> produtos = produtoRepository.findAllById(request.getProdutoId());

        ListadeDesejos lista = new ListadeDesejos();
        lista.setCliente(cliente);
        lista.setNome(request.getNomeLista());
        lista.setProdutos(produtos);

        listadeDesejosRepository.save(lista);

        ListadeDesejosResponseDTO dto = new ListadeDesejosResponseDTO();
        dto.setIdCliente(lista.getId());
        dto.setNomeLista(request.getNomeLista());
        dto.setLivros(
	                lista.getProdutos()
	                .stream()
	                .map(Produto::getNome)
	                .toList());
        return dto;



    }

//    // ------------------ buscar por id ------------------
//    public ListadeDesejosResponseDTO buscarPorId(Long id) {
//        ListadeDesejos l = listadeDesejosRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Lista de Desejos não encontrada: " + id));
//        return toResponseDTO(l);
//    }
//
//    // ------------------ listar por cliente ------------------
//    public List<ListadeDesejosResponseDTO> listarPorCliente(Long clienteId) {
//        return listadeDesejosRepository.findByClienteId(clienteId).stream()
//                .map(this::toResponseDTO)
//                .toList();
//    }
//
//    // ------------------ adicionar produto ------------------
//    public void adicionarProduto(Long listadeDesejosId, Long produtoId) {
//        ListadeDesejos l = listadeDesejosRepository.findById(listadeDesejosId)
//                .orElseThrow(() -> new RuntimeException("Lista não encontrada: " + listadeDesejosId));
//        Produto p = produtoRepository.findById(produtoId)
//                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoId));
//        l.getProdutos().add(p);
//        listadeDesejosRepository.save(l);
//    }
//
//    // ------------------ remover produto ------------------
//    public void removerProduto(Long listadeDesejosId, Long produtoId) {
//        ListadeDesejos l = listadeDesejosRepository.findById(listadeDesejosId)
//                .orElseThrow(() -> new RuntimeException("Lista não encontrada: " + listadeDesejosId));
//        Produto p = produtoRepository.findById(produtoId)
//                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoId));
//        l.getProdutos().remove(p);
//        listadeDesejosRepository.save(l);
//    }
//
//    // ------------------ converter Entity → ResponseDTO ------------------
//    private ListadeDesejosResponseDTO toResponseDTO(ListadeDesejos l) {
//        ListadeDesejosResponseDTO dto = new ListadeDesejosResponseDTO();
//        dto.setId(l.getId());
//        dto.setClienteId(l.getCliente().getId());
//        dto.setNome(l.getNome());
//        dto.setProdutoId(l.getProdutos().stream().map(Produto::getId).toList());
//        return dto;
//    }
}
