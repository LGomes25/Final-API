package org.serratec.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Isbn {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String isbn;
	private String titulo;
    
	@Lob
	@Column(name = "descricao")
	private String descricao;
    private String numeroPaginas;
    private String dataPublicacao;
    private String editoras;
    private String autores;
    private String urlCapa;
    
}