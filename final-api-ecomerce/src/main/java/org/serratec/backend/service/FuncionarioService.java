package org.serratec.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.backend.dto.FuncionarioRequestDTO;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.exception.FuncionarioException;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Funcionario toEntity(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setCpf(dto.getCpf());
        funcionario.setSenha(passwordEncoder.encode(dto.getSenha()));
        funcionario.setSalario(dto.getSalario());
        funcionario.setDataAdmissao(dto.getDataAdmissao());
        return funcionario;
    }

    private FuncionarioResponseDTO toDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getCpf(),
                funcionario.getSalario(),
                funcionario.getDataAdmissao()
        );
    }

    public List<FuncionarioResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new FuncionarioException("Funcionário não encontrado"));
    }

    public FuncionarioResponseDTO cadastrar(FuncionarioRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new FuncionarioException("Email já cadastrado");
        }
        if (repository.existsByCpf(dto.getCpf())) {
            throw new FuncionarioException("CPF já cadastrado");
        }

        Funcionario funcionario = toEntity(dto);
        return toDTO(repository.save(funcionario));
    }

    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new FuncionarioException("Funcionário não encontrado"));

        if (!funcionario.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new FuncionarioException("Email já cadastrado");
        }
        if (!funcionario.getCpf().equals(dto.getCpf()) && repository.existsByCpf(dto.getCpf())) {
            throw new FuncionarioException("CPF já cadastrado");
        }

        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setCpf(dto.getCpf());
        funcionario.setSalario(dto.getSalario());
        funcionario.setDataAdmissao(dto.getDataAdmissao());

        return toDTO(repository.save(funcionario));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new FuncionarioException("Funcionário não encontrado");
        }
        repository.deleteById(id);
    }
}