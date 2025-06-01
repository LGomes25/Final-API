package org.serratec.backend.repository;

import org.serratec.backend.entity.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsbnRepository extends JpaRepository<Isbn, Long>{

	Isbn findByIsbn(String isbn);
	
}
