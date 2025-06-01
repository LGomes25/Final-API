package org.serratec.backend.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Funcionario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "Telefone inválido. Formato valido apenas digitos: xxxxxxxxxx")
	private String telefone;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{8}", message = "CEP inválido. Deve conter exatamente 8 dígitos numéricos sem traço!!!!!!")
    private String cep;

    
    @NotNull(message = "Salário é obrigatório")
    @Positive(message = "Salário deve ser positivo")
    private Double salario;

    @NotNull(message = "Data de admissão é obrigatória")
    @PastOrPresent
    private LocalDate dataAdmissao;

//	Inserir o perfil em clientes
//	private Set<FuncionarioPerfil> clientePerfis = new HashSet<>();

//	construtor cheio, o vazio esta como lombock
	public FuncionarioRequestDTO(Funcionario cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.senha = cliente.getSenha();
	}
	
//	construtor para requisitar a informacao do cep
	public FuncionarioRequestDTO(String nome, String email, String telefone,String cpf, String senha, /*Set<FuncionarioPerfil> clientePerfis,*/ String cep) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
//      this.clientePerfis = clientePerfis;
        this.cep = cep;
    }


}