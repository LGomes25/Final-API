package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ListadeDesejosDTO;
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
        ListadeDesejosService listadeDesejosService = new ListadeDesejosService();
//        listadeDesejos.setId(dto.getId());
//        listadeDesejos.setNome(dto.getNome());
        List<Produto> produtos = new ArrayList<>();
        if (dto.getProdutos() != null) {
            for (Long idProduto : dto.getProdutos()) {
                produtos.add(produtoRepository.findById(idProduto).orElseThrow());
            }

        }
        return null;
    }
}
