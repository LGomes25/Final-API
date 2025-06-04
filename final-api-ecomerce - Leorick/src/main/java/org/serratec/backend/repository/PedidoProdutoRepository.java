package org.serratec.backend.repository;

import org.serratec.backend.entity.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
    List<PedidoProduto> findByPedidoId(Long pedidoId);

    }


