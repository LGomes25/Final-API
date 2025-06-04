package org.serratec.backend.repository;

import org.serratec.backend.entity.FuncionarioPerfil;
import org.serratec.backend.entity.PK.FuncionarioPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioPerfilRepository extends JpaRepository<FuncionarioPerfil, FuncionarioPerfilPK>{
	
}
