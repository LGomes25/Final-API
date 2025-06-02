package org.serratec.backend.repository;

import org.serratec.backend.entity.ListadeDesejos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListadeDesejosRepository extends JpaRepository<ListadeDesejos, Long> {
    List<ListadeDesejos> findListadeDesejosByNome(String nome);


}
