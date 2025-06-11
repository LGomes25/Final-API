package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.dto.AvaliacaoRequestDTO;
import org.serratec.backend.dto.AvaliacaoResponseDTO;
import org.serratec.backend.entity.Avaliacao;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.*;
import org.serratec.backend.repository.AvaliacaoRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //Listar todas as avaliacoes
    public List<AvaliacaoResponseDTO> listarTodas(){
    	List<Avaliacao> todas = avaliacaoRepository.findAll();
    	List<AvaliacaoResponseDTO> todasDto = new ArrayList<>();
    	for (Avaliacao todasParaDto : todas) {
			todasDto.add(new AvaliacaoResponseDTO(
											todasParaDto.getId()
											,todasParaDto.getNota()
											,todasParaDto.getProduto().getId()
											,todasParaDto.getProduto().getNome()
											,todasParaDto.getCliente().getId()
											,todasParaDto.getCliente().getNome()
											));
		}
    	return todasDto;
    }
    
    //listar por ID do produto
    public List<AvaliacaoResponseDTO> listarPorProduto(Long idProduto) {
        return avaliacaoRepository.findByProdutoId(idProduto).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    //inserir uma nova avaliacao
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
    
    //atualizar uma avaliacao existente 
    public AvaliacaoResponseDTO atualizarPorProduto(AvaliacaoRequestDTO avaliacaoRequestDTO) {
    	Optional<Produto> produto = produtoRepository.findById(avaliacaoRequestDTO.getIdProduto());
    	if(!produto.isPresent()) {
    		throw new ProdutoException("Produto de id "+ avaliacaoRequestDTO.getIdProduto() + " não encontrado.");
    	}
    	Optional<Cliente> cliente = clienteRepository.findById(avaliacaoRequestDTO.getIdCliente());
    	if(!cliente.isPresent()) {
    		throw new ClienteException("Cliente de id "+ avaliacaoRequestDTO.getIdCliente() + " não encontrado.");
    	} 
    	if (!avaliacaoRepository.existsByProdutoIdAndClienteId(produto.get().getId(), cliente.get().getId())) {
            throw new AvaliacaoExistenteException("Avaliação de '"+cliente.get().getNome()+"' para o livro '"+produto.get().getNome()+"' não realizada.");
        }
    	
    	List<Avaliacao> avaliacoesProduto = avaliacaoRepository.findByProdutoId(produto.get().getId());
    	System.out.println(avaliacoesProduto);
    	
    	Optional<Avaliacao> avaliacaoprodutoCliente = avaliacoesProduto.stream()
    																	.filter(e -> cliente.isPresent())
    																	.findFirst();
    	System.out.println(avaliacaoprodutoCliente);
    	
    	Avaliacao avaliacao = new Avaliacao();
    	avaliacao.setId(avaliacaoprodutoCliente.get().getId());
    	avaliacao.setNota(avaliacaoRequestDTO.getNota());
        avaliacao.setProduto(produto.get());
        avaliacao.setCliente(cliente.get());
        avaliacaoRepository.save(avaliacao);

        return new AvaliacaoResponseDTO(avaliacao);
    }
    
    //Remover uma avaliacao
    public void remover(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
        	throw new AvaliacaoExistenteException("Avaliação não encontrada");
        }
    	avaliacaoRepository.deleteById(id);
    }
}