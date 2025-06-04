package org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Funcionario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
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
    @Pattern(regexp = "\\d{8}", message = "CEP inválido. Deve conter exatamente 8 dígitos numéricos sem traço!")
    private String cep;

    
    @NotNull(message = "Salário é obrigatório")
    @Positive(message = "Salário deve ser positivo")
    @Min(value = 1518, message = "Salário mínimo permitido é de 1518.00." )
    private Double salario;

    @NotNull(message = "Data de admissão é obrigatória")
    @PastOrPresent
    private LocalDate dataAdmissao;

    @NotNull(message = "Deve ser informado pelo menos um perfil.")
    private Set<Long> perfisIds;

//	construtor cheio, o vazio esta como lombock
	public FuncionarioRequestDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.telefone = funcionario.getTelefone();
		this.email = funcionario.getEmail();
		this.cpf = funcionario.getCpf();
		this.senha = funcionario.getSenha();
		this.salario = funcionario.getSalario();
		this.dataAdmissao = funcionario.getDataAdmissao();
	}
	
//	construtor para requisitar a informacao do cep
	public FuncionarioRequestDTO(String nome, String email, String telefone,String cpf, String senha, Double salario, LocalDate dataAdmissao, Set<Long> perfisIds, String cep) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.salario = salario;
		this.dataAdmissao = dataAdmissao;
		this.perfisIds = perfisIds;
        this.cep = cep;
    }

}