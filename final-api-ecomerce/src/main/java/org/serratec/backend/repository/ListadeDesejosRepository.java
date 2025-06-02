package org.serratec.backend.repository;

import java.util.List;

import org.serratec.backend.entity.ListadeDesejos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListadeDesejosRepository extends JpaRepository<ListadeDesejos, Long> {
    
	List<ListadeDesejos> findListadeDesejosByNome(String nome);

	List<ListadeDesejos> findByClienteId(Long clienteId);



}
