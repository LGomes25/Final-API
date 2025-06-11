package org.serratec.backend.repository;

import org.serratec.backend.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome (String nome);
    Optional<Produto> findById (Long id);
    Page<Produto> findByPrecoBetween(Double faixa1, Double faixa2, Pageable pageable);
}