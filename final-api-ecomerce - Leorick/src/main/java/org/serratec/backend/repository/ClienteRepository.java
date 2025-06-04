package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByEmail(String email);
	Optional<Cliente> findByCpf(String email);
	
}
