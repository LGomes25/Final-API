package org.serratec.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.serratec.backend.enums.StatusPedido;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime dataPedido; 
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status; 

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoProduto> produtosPedidos;

	public void setProduto(List<PedidoProduto> listaPedidoProduto) {
	}
	
	public LocalDate getData() {
		return null;
	}

	public void setData(LocalDate now) {
	}
	
}
