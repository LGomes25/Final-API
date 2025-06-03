package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Optional<Categoria> findById(Long id);
	
}
