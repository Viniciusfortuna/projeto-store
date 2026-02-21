package com.example.store.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.store.enums.StatusPedido;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pedidos")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private Double pesoBruto;
	
	@NotNull
	private Double pesoLiquido;
	
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetails> items = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "usuario_geracao")
	private User usuarioGeracao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_atualizacao")
	private User usuarioAtualizacao;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@PrePersist
	public void createdAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate 
	public void updatedAt() {
		this.updatedAt = LocalDateTime.now();
	}
	
	public void addItem(OrderDetails item) {
	    item.setOrderId(this);
	    this.items.add(item);
	}
}
