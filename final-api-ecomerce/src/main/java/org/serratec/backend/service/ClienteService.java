package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private EnderecoService endServ;
	
	public List<ClienteResponseDTO> listar(){
		List<Cliente> clientes = repoCliente.findAll();
		List<ClienteResponseDTO> clientesDTO = new ArrayList<>();
		for(Cliente cliente : clientes) {
			clientesDTO.add(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone(),cliente.getEmail()));
		}
		return clientesDTO;
	}
	
	@Transactional
	public ClienteResponseDTO inserir(ClienteRequestDTO cliente) {
		Optional<Cliente> cpfExp = repoCliente.findByCpf(cliente.getCpf());
		Optional<Cliente> emailExp = repoCliente.findByEmail(cliente.getEmail());
		
		endServ.buscar(cliente.getCep());									//Verifica/cria o endereco na tabela
		Endereco end = endServ.buscarEndereco(cliente.getCep());			//Chama o metodo em endereco service
		
		if(cpfExp.isPresent()) {
			throw new ClienteException("CPF já cadastrado");
		}
		if(emailExp.isPresent()) {
			throw new ClienteException("Email já cadastrado");
		}
		
		
		Cliente clienteEntity = new Cliente();
		clienteEntity.setNome(cliente.getNome());
		clienteEntity.setEmail(cliente.getEmail());
		clienteEntity.setTelefone(cliente.getTelefone());
		clienteEntity.setCpf(cliente.getCpf());
		clienteEntity.setSenha(passwordEncoder.encode(cliente.getSenha()));
		clienteEntity.setEndereco(end);									//Inclui endereco no objeto
		
		clienteEntity=repoCliente.save(clienteEntity);
		
//		mailConfig.enviar(clienteEntity.getEmail(), "Confirmação de Cadastro", cliente.toString());
	
		return new ClienteResponseDTO(clienteEntity.getId(), clienteEntity.getNome(), clienteEntity.getTelefone(),clienteEntity.getEmail());
	}

	@Transactional
	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO cliente) {
		Optional<Cliente> clienteValido = repoCliente.findById(id);
		if(!clienteValido.isPresent()) {
			throw new ClienteException("Id "+id+" não existe no sistema");
		}
		
		endServ.buscar(cliente.getCep());									//Verifica/cria o endereco na tabela
		Endereco end = endServ.buscarEndereco(cliente.getCep());
		
		Cliente clienteEntity = clienteValido.get();
		clienteEntity.setNome(cliente.getNome() != null ? cliente.getNome() : clienteEntity.getNome());
		clienteEntity.setEmail(cliente.getEmail() != null ? cliente.getEmail() : clienteEntity.getEmail());
		clienteEntity.setTelefone(cliente.getTelefone()!= null ? cliente.getTelefone() : clienteEntity.getTelefone());
		clienteEntity.setCpf(cliente.getCpf()!= null ? cliente.getCpf() : clienteEntity.getCpf());
		clienteEntity.setEndereco(end);
		
		clienteEntity=repoCliente.save(clienteEntity);
		
//		mailConfig.enviar(clienteEntity.getEmail(), "Confirmação de Atualização de Cadastro", cliente.toString());
		
		return new ClienteResponseDTO(clienteEntity.getId(), 
										clienteEntity.getNome(), 
										clienteEntity.getTelefone(),
										clienteEntity.getEmail());

	}

	@Transactional
	public void remover(Long id) {
		Optional<Cliente> clienteExistente = repoCliente.findById(id);
		if(!clienteExistente.isPresent()) {
			throw new ClienteException("Id "+id+" não existe no sistema");
		}
		repoCliente.deleteById(id);
	}
	
}	