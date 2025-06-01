package org.serratec.backend.dto;

import org.serratec.backend.entity.Isbn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class IsbnResponseDTO {
	private String isbn;
	private String titulo;
    private String descricao;
    private String numeroPaginas;
    private String dataPublicacao;
    private String editoras;
    private String autores;
    private String urlCapa;

	public IsbnResponseDTO(Isbn isbn) {
		this.isbn=isbn.getIsbn();
		this.titulo = isbn.getTitulo();
		this.descricao = isbn.getDescricao();
		this.numeroPaginas = isbn.getNumeroPaginas();
		this.dataPublicacao = isbn.getDataPublicacao();
		this.editoras = isbn.getEditoras();
		this.autores = isbn.getAutores();
		this.urlCapa = isbn.getUrlCapa();
	}

	public IsbnResponseDTO(String isbn, String titulo, String descricao, String numeroPaginas, String dataPublicacao,
			String editoras, String autores, String capa) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.descricao = descricao;
		this.numeroPaginas = numeroPaginas;
		this.dataPublicacao = dataPublicacao;
		this.editoras = editoras;
		this.autores = autores;
		this.urlCapa = capa;
	}
    
    
}