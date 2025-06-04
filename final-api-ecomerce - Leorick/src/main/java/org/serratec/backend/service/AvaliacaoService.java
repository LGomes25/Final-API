package org.serratec.backend.service;

import org.serratec.backend.dto.AvaliacaoRequestDTO;
import org.serratec.backend.dto.AvaliacaoResponseDTO;
import org.serratec.backend.entity.Avaliacao;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.AvaliacaoExistenteException;
import org.serratec.backend.exception.ClienteNotFoundException;
import org.serratec.backend.exception.ProdutoNotFoundException;
import org.serratec.backend.repository.AvaliacaoRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<AvaliacaoResponseDTO> listarPorProduto(Long idProduto) {
        return avaliacaoRepository.findByProdutoId(idProduto).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public AvaliacaoResponseDTO inserir(AvaliacaoRequestDTO avaliacaoRequestDTO) {
        Produto produto = produtoRepository.findById(avaliacaoRequestDTO.getIdProduto())
                .orElseThrow(() -> new ProdutoNotFoundException(avaliacaoRequestDTO.getIdProduto()));

        Cliente cliente = clienteRepository.findById(avaliacaoRequestDTO.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(avaliacaoRequestDTO.getIdCliente()));

        if (avaliacaoRepository.existsByProdutoIdAndClienteId(produto.getId(), cliente.getId())) {
            throw new AvaliacaoExistenteException("Este cliente já avaliou este produto");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoRequestDTO.getNota());
        avaliacao.setProduto(produto);
        avaliacao.setCliente(cliente);

        return new AvaliacaoResponseDTO(avaliacaoRepository.save(avaliacao));
    }

    public void remover(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}