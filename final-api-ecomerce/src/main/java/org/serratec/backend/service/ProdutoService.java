package org.serratec.backend.service;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Categoria;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.ProdutoException;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//Lista todos os produtos junto com suas categorias
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

//busca o produto pelo id
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto com ID " + id + " não encontrado."));
        return new ProdutoResponseDTO(produto);
    }

    //insere um novo produto conectando ele a uma categoria
    @Transactional
    public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoRequestDTO) {
        if (produtoRequestDTO.getIdCategoria() == null) {
            throw new ProdutoException("A categoria é obrigatória para inserir um produto.");
        }
        if (produtoRepository.findByNome(produtoRequestDTO.getNome()).isPresent()) {
            throw new ProdutoException("Já existe um produto com o nome: " + produtoRequestDTO.getNome());
        }

        // só vai funcionar quando tivermos as classes da Categoria prontas
        Categoria categoria = categoriaService.buscar(produtoRequestDTO.getIdCategoria());

        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setCategoria(categoria);

        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    // atauliza um produto
    @Transactional
    public ProdutoResponseDTO editar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto com ID " + id + " não encontrado para atualização."));


        Optional<Produto> produtoComMesmoNome = produtoRepository.findByNome(produtoRequestDTO.getNome());
        if (produtoComMesmoNome.isPresent() && !produtoComMesmoNome.get().getId().equals(id)) {
            throw new ProdutoException("Já existe outro produto com o nome: " + produtoRequestDTO.getNome());
        }

        //atualiza o nome  e o preço do produto
        produto.setNome(produtoRequestDTO.getNome());
        produto.setPreco(produtoRequestDTO.getPreco());

        if (produtoRequestDTO.getIdCategoria() != null) {
            Categoria categoria = categoriaService.buscar(produtoRequestDTO.getIdCategoria());
            produto.setCategoria(categoria);
        }

        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    // remove o produto pelo id
    @Transactional
    public void remover(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Produto" + id + " não encontrado para remoção."));

        produtoRepository.delete(produto);
    }

}