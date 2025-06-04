package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByCpf(String cpf);

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    interface PedidoProdutoRepository {
    }

}