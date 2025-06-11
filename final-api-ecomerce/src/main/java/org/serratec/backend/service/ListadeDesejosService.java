package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.dto.ListadeDesejosRequestDTO;
import org.serratec.backend.dto.ListadeDesejosResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ListadeDesejos;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.ListadeDesejosException;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ListadeDesejosRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ListadeDesejosService {

    @Autowired
    private ListadeDesejosRepository listadeDesejosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //Listar todas as listas
    public List<ListadeDesejosResponseDTO> listarTodas(){
    	List<ListadeDesejos> todasListas = listadeDesejosRepository.findAll();
    	
    	List<ListadeDesejosResponseDTO> todasListasDTO = new ArrayList<>();
    	for (ListadeDesejos ld : todasListas) {
    		todasListasDTO.add(toResponseDTO(ld));
		}
    	return todasListasDTO;
    }

    //Criar lista de desejos
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
        dto.setId(lista.getId());
        dto.setIdCliente(cliente.getId());
        dto.setNomeLista(request.getNomeLista());
        dto.setLivros(
	                lista.getProdutos()
	                .stream()
	                .map(Produto::getNome)
	                .toList());
        return dto;
    }

    //Buscar por id da Lista
    public ListadeDesejosResponseDTO buscarPorId(Long id) {
        ListadeDesejos listaPorId = listadeDesejosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista de Desejos não encontrada: " + id));
        return toResponseDTO(listaPorId);
    }

    //Buscar lista por id cliente
    public List<ListadeDesejosResponseDTO> listarPorCliente(Long clienteId) {
        return listadeDesejosRepository.findByClienteId(clienteId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
	public void removerLista(Long id) {
		Optional<ListadeDesejos> listaExistente = listadeDesejosRepository.findById(id);
		if(!listaExistente.isPresent()) {
			throw new ListadeDesejosException("Lista Id "+id+" não existe no sistema");
		}
		listadeDesejosRepository.deleteById(id);
	}
    
    // converter Entity para ResponseDTO
    private ListadeDesejosResponseDTO toResponseDTO(ListadeDesejos l) {
        ListadeDesejosResponseDTO dto = new ListadeDesejosResponseDTO();
        dto.setId(l.getId());
        dto.setIdCliente(l.getCliente().getId());
        dto.setNomeLista(l.getNome());
        dto.setLivros(l.getProdutos().stream().map(Produto::getNome).toList());
        return dto;
    }
}
