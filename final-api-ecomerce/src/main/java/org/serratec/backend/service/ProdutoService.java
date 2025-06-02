package org.serratec.backend.service;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Categoria;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.ProdutoException;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto com ID " + id + " não encontrado."));
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoRequestDTO) {
        if (produtoRequestDTO.getIdCategoria() == null) {
            throw new ProdutoException("A categoria é obrigatória para inserir um produto.");
        }
        if (produtoRepository.findByNome(produtoRequestDTO.getNome()).isPresent()) {
            throw new ProdutoException("Já existe um produto com o nome: " + produtoRequestDTO.getNome());
        }

        Categoria categoria = categoriaService.buscarEntidadePorId(produtoRequestDTO.getIdCategoria());

        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setCategoria(categoria);

        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO editar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto com ID " + id + " não encontrado para atualização."));

        Optional<Produto> produtoComMesmoNome = produtoRepository.findByNome(produtoRequestDTO.getNome());
        if (produtoComMesmoNome.isPresent() && !produtoComMesmoNome.get().getId().equals(id)) {
            throw new ProdutoException("Já existe outro produto com o nome: " + produtoRequestDTO.getNome());
        }

        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());

        if (produtoRequestDTO.getIdCategoria() != null) {
            Categoria categoria = categoriaService.buscarEntidadePorId(produtoRequestDTO.getIdCategoria());
            produto.setCategoria(categoria);
        }

        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public void remover(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto" + id + " não encontrado. Não pode ser removido."));
        produtoRepository.delete(produto);
    }

    public Page<ProdutoResponseDTO> listarPorPagina(Pageable pageable) {
        Page<Produto> produto = produtoRepository.findAll(pageable);
        return produto.map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> listarPorPaginaFaixaPreco(Double faixa1, Double faixa2, Pageable pageable) {
        Page<Produto> produto = produtoRepository.findByPrecoBetween(faixa1, faixa2, pageable);
        return produto.map(ProdutoResponseDTO::new);
    }
}

