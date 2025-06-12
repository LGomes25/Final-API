package org.serratec.backend.entity.PK;

import java.util.Objects;

import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.entity.Perfil;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class FuncionarioPerfilPK {

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(perfil, funcionario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioPerfilPK other = (FuncionarioPerfilPK) obj;
		return Objects.equals(perfil, other.perfil) && Objects.equals(funcionario, other.funcionario);
	}
}