package org.serratec.backend.repository;

import org.serratec.backend.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByProdutoId(Long produtoId);
    boolean existsByProdutoIdAndClienteId(Long produtoId, Long clienteId);
}