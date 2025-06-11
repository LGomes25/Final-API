package org.serratec.backend.entity;

import java.time.LocalDate;

import org.serratec.backend.entity.PK.FuncionarioPerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class FuncionarioPerfil {

	@EmbeddedId
	private FuncionarioPerfilPK id = new FuncionarioPerfilPK();

	private LocalDate dataCriacao;

	public FuncionarioPerfil(Funcionario funcionario, Perfil perfil, LocalDate dataCriacao) {
		id.setFuncionario(funcionario);
		id.setPerfil(perfil);
		this.dataCriacao = dataCriacao;
	}

	public void setFuncionario(Funcionario funcionario) {
		id.setFuncionario(funcionario);
	}

	public Funcionario getFuncionario() {
		return id.getFuncionario();
	}

	public void setPerfil(Perfil perfil) {
		id.setPerfil(perfil);
	}

	public Perfil getPerfil() {
		return id.getPerfil();
	}

	public FuncionarioPerfilPK getId() {
		return id;
	}

	public void setId(FuncionarioPerfilPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	//Inclusão para teste de exclusão de perfil na atualização
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuncionarioPerfil that = (FuncionarioPerfil) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}