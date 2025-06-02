package org.serratec.backend.entity;

import java.time.LocalDate;

import org.serratec.backend.entity.PK.FuncionarioPerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity
public class FuncionarioPerfil {

	@EmbeddedId
	private FuncionarioPerfilPK id = new FuncionarioPerfilPK();

	private LocalDate dataCriacao;

	public FuncionarioPerfil() {
	}

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

}