package org.serratec.backend.dto;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ClienteRequestDTO {

	@NotBlank(message = "O nome é obrigatório.")
	@Column(nullable = false)
	private String nome;

	@NotBlank(message = "O email é obrigatório.")
	@Email(message = "Email inválido")
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "O Telefone é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "Telefone inválido. Formato valido apenas digitos: xxxxxxxxxx")
	private String telefone;
	
	@NotBlank(message = "O CPF é obrigatório.")
	@CPF(message = "CPF inválido")
	@Column(unique = true, nullable = false)
	private String cpf;

	@NotBlank(message = "Senha é obrigatória")
	@NotBlank
	private String senha;
	
	@NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{8}", message = "CEP inválido. Deve conter exatamente 8 dígitos numéricos sem traço!!!!!!")
    private String cep;

//	Inserir o perfil em clientes
//	private Set<ClientePerfil> clientePerfis = new HashSet<>();

	
//	construtor cheio, o vazio esta como lombock
	public ClienteRequestDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.senha = cliente.getSenha();
	}
	
//	construtor para requisitar a informacao do cep
	public ClienteRequestDTO(String nome, String email, String telefone,String cpf, String senha, /*Set<ClientePerfil> clientePerfis,*/ String cep) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
//        this.clientePerfis = clientePerfis;
        this.cep = cep;
    }
	
}

