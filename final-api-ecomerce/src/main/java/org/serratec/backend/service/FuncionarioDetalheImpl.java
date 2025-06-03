package org.serratec.backend.service;

import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioDetalheImpl implements UserDetailsService {
	private FuncionarioRepository repository;

	// Injeção de dependência através do construtor passando o repositório
	public FuncionarioDetalheImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

		return funcionario;
	}

}
