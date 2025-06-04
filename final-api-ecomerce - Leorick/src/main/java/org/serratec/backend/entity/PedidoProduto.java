package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class PedidoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantidade;
	private Double valorVenda;
	private Double desconto;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	}

