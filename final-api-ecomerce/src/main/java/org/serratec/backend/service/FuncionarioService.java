package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.FuncionarioRequestDTO;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.exception.FuncionarioException;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repoFuncionario;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
//	@Autowired
//	private PerfilService perfilService;
//	
//	@Autowired
//	private FuncionarioPerfilRepository funcionarioPerfilRepository;
	
	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private EnderecoService endServ;

	public List<FuncionarioResponseDTO> listar(){
		List<Funcionario> funcionarios = repoFuncionario.findAll();
		List<FuncionarioResponseDTO> funcionariosDTO = new ArrayList<>();
		for(Funcionario funcionario : funcionarios) {
			funcionariosDTO.add(new FuncionarioResponseDTO(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(),funcionario.getEmail()));
		}
		return funcionariosDTO;
	}
	
	@Transactional
	public FuncionarioResponseDTO inserir(FuncionarioRequestDTO funcionario) {
		Optional<Funcionario> cpfExp = repoFuncionario.findByCpf(funcionario.getCpf());
		Optional<Funcionario> emailExp = repoFuncionario.findByEmail(funcionario.getEmail());
		
		endServ.buscar(funcionario.getCep());									//Verifica/cria o endereco na tabela
		Endereco end = endServ.buscarEndereco(funcionario.getCep());			//Chama o metodo em endereco service
		
		if(cpfExp.isPresent()) {
			throw new FuncionarioException("CPF já cadastrado");
		}
		if(emailExp.isPresent()) {
			throw new FuncionarioException("Email já cadastrado");
		}
		
		
		Funcionario funcionarioEntity = new Funcionario();
		funcionarioEntity.setNome(funcionario.getNome());
		funcionarioEntity.setTelefone(funcionario.getTelefone());
		funcionarioEntity.setEmail(funcionario.getEmail());
		funcionarioEntity.setCpf(funcionario.getCpf());
		funcionarioEntity.setSenha(passwordEncoder.encode(funcionario.getSenha()));
		funcionarioEntity.setSalario(funcionario.getSalario());
		funcionarioEntity.setDataAdmissao(funcionario.getDataAdmissao());
		funcionarioEntity.setEndereco(end);									//Inclui endereco no objeto
		
//		for (FuncionarioPerfil up: funcionario.getFuncionarioPerfis()) {
//			up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
//			up.setFuncionario(funcionarioEntity);
//			up.setDataCriacao(LocalDate.now());
//		}
		
		funcionarioEntity=repoFuncionario.save(funcionarioEntity);
		
//		funcionarioPerfilRepository.saveAll(funcionario.getFuncionarioPerfis());
		
		mailConfig.enviar(funcionarioEntity.getEmail(), "Confirmação de Cadastro", funcionario.toString());
	
		return new FuncionarioResponseDTO(funcionarioEntity.getId(), funcionarioEntity.getNome(), funcionarioEntity.getTelefone(),funcionarioEntity.getEmail());
	}

	@Transactional
	public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO funcionario) {
		Optional<Funcionario> funcionarioValido = repoFuncionario.findById(id);
		if(!funcionarioValido.isPresent()) {
			throw new FuncionarioException("Id "+id+" não existe no sistema");
		}
		
		endServ.buscar(funcionario.getCep());									//Verifica/cria o endereco na tabela
		Endereco end = endServ.buscarEndereco(funcionario.getCep());
		
		Funcionario funcionarioEntity = funcionarioValido.get();
		funcionarioEntity.setNome(funcionario.getNome() != null ? funcionario.getNome() : funcionarioEntity.getNome());
		funcionarioEntity.setEmail(funcionario.getEmail() != null ? funcionario.getEmail() : funcionarioEntity.getEmail());
		funcionarioEntity.setTelefone(funcionario.getTelefone() != null ? funcionario.getTelefone() : funcionarioEntity.getTelefone());
		funcionarioEntity.setCpf(funcionario.getCpf() != null ? funcionario.getCpf() : funcionarioEntity.getCpf());
		funcionarioEntity.setSalario(funcionario.getSalario() != null ? funcionario.getSalario() : funcionarioEntity.getSalario());
		funcionarioEntity.setDataAdmissao(funcionario.getDataAdmissao() != null ? funcionario.getDataAdmissao() : funcionarioEntity.getDataAdmissao());
		funcionarioEntity.setEndereco(end);
		
		funcionarioEntity=repoFuncionario.save(funcionarioEntity);
		
		mailConfig.enviar(funcionarioEntity.getEmail(), "Confirmação de Atualização de Cadastro", funcionario.toString());
		
		return new FuncionarioResponseDTO(funcionarioEntity.getId(), 
										funcionarioEntity.getNome(), 
										funcionarioEntity.getTelefone(),
										funcionarioEntity.getEmail());

	}

	@Transactional
	public void remover(Long id) {
		Optional<Funcionario> funcionarioExistente = repoFuncionario.findById(id);
		if(!funcionarioExistente.isPresent()) {
			throw new FuncionarioException("Id "+id+" não existe no sistema");
		}
		repoFuncionario.deleteById(id);
	}
	
}	