package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório.")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "O telefone é obrigatório.")
	@Column(nullable = false)
	private String telefone;
	
	@NotBlank(message = "O email é obrigatório.")
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "O CPF é obrigatório.")
	@Column(unique = true, nullable = false)
	private String cpf;
	
	@NotBlank
	private String senha;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	
//	@OneToMany(mappedBy = "id.cliente", fetch = FetchType.EAGER)
//	private Set<ClientePerfil> clientePerfis = new HashSet<>();
	
}
