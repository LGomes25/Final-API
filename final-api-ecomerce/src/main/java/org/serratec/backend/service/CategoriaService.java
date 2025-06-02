package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import org.serratec.backend.dto.CategoriaRequestDTO;
import org.serratec.backend.dto.CategoriaResponseDTO;
import org.serratec.backend.entity.Categoria;
import org.serratec.backend.exception.CategoriaException;
import org.serratec.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repoCategoria;


	public List<CategoriaResponseDTO> listar() {
		List<Categoria> categorias = repoCategoria.findAll();
		List<CategoriaResponseDTO> categoriasDTO = new ArrayList<>();
		for (Categoria categoria : categorias) {
			categoriasDTO.add(new CategoriaResponseDTO(categoria.getId(), categoria.getNome()));
		}
		return categoriasDTO;
	}

	@Transactional
	public CategoriaResponseDTO inserir(CategoriaRequestDTO categoria) {

		Categoria categoriaEntity = new Categoria();
		categoriaEntity.setNome(categoria.getNome());

		categoriaEntity = repoCategoria.save(categoriaEntity);

		return new CategoriaResponseDTO(categoriaEntity.getId(), categoriaEntity.getNome());
	}

	@Transactional
	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoria) {
		Optional<Categoria> categoriaValido = repoCategoria.findById(id);
		if (!categoriaValido.isPresent()) {
			throw new CategoriaException("Id " + id + " não existe no sistema");
		}

		Categoria categoriaEntity = categoriaValido.get();
		categoriaEntity.setNome(categoria.getNome() != null ? categoria.getNome() : categoriaEntity.getNome());

		categoriaEntity = repoCategoria.save(categoriaEntity);

		return new CategoriaResponseDTO(categoriaEntity.getId(),
				categoriaEntity.getNome());
	}

	@Transactional
	public void remover(Long id) {
		Optional<Categoria> categoriaExistente = repoCategoria.findById(id);
		if (!categoriaExistente.isPresent()) {
			throw new CategoriaException("Id " + id + " não existe no sistema");
		}
		repoCategoria.deleteById(id);
	}

	public Categoria buscarEntidadePorId(@NotNull(message = "O ID da categoria é obrigatório.") Long idCategoria) {
        return null;
    }

}

