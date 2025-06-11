package org.serratec.backend.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Avaliacao;
import org.serratec.backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
	Optional<Avaliacao> findByProduto(Produto produto);
	
    List<Avaliacao> findByProdutoId(Long produtoId);
    
    boolean existsByProdutoIdAndClienteId(Long produtoId, Long clienteId);
}