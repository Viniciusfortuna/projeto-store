package com.example.store.entity;
import com.example.store.pk.ItemSequence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "itens_pedidos")
public class OrderDetails {
	
	@EmbeddedId
	private ItemSequence itemSequence;
	
	@ManyToOne
	@MapsId("order_id")
	@JoinColumn(name = "order_id")
	private Order orderId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	
	@NotNull
	private Double precoUnitario;
	
	private Double quantidade;
	
	private Double valorTotal;
	
}
