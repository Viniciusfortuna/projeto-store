package com.example.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.dto.request.OrderDetailsRequestDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.Order;
import com.example.store.entity.OrderDetails;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import com.example.store.mapper.OrderMapper;
import com.example.store.pk.ItemSequence;
import com.example.store.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	long cont = 0;
	
	double valorTotIte;
	double pesoBru;
	double pesoLiq;
	double valorTot;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public OrderResponseDTO salvar(OrderRequestDTO dto) {

		Customer customer = customerService.buscarPorIdEntity(dto.customerCode());
		User user = userService.buscarUsuarioPorId(dto.usuarioGeracao());
		
		Order order = new Order();
		
		order.setCustomer(customer);
		order.setUsuarioGeracao(user);
		
		if(dto.items() != null) {		
			cont =  1;
			pesoBru = 0;
			pesoLiq = 0;
			
			for(OrderDetailsRequestDTO dtoDetails : dto.items()) {
				OrderDetails orderDetails = new OrderDetails();
			
				ItemSequence pk = new ItemSequence();
				pk.setOrder_sequence(cont);
				orderDetails.setItemSequence(pk);
				
				Product product = productService.listarPorIdEntity(dtoDetails.productId());
				orderDetails.setProductId(product);
				orderDetails.setPrecoUnitario(dtoDetails.precoUnitario());
				orderDetails.setQuantidade(dtoDetails.quantidade());
				orderDetails.setValorTotal(dtoDetails.valorTotal());
				
				/*------------- Validações Itens de Pedido ------------*/
				valorTotIte = dtoDetails.precoUnitario() * dtoDetails.quantidade();
				
				if(valorTotIte != dtoDetails.valorTotal()) throw new RuntimeException("O valor total não corresponde "
				+ "aos preço unitário * quantidade! " + "Produto ID: " + product.getId() + "-" + product.getNome() + " SEQ: " + pk.getOrder_sequence());
				
				pesoBru = pesoBru + (product.getPesoBruto() * dtoDetails.quantidade());
				pesoLiq = pesoLiq +  (product.getPesoLiquido() * dtoDetails.quantidade());
				valorTot = valorTot + dtoDetails.valorTotal();
				
				order.addItem(orderDetails);		
				cont++;
			}
		}
		
		order.setPesoBruto(pesoBru);
		order.setPesoLiquido(pesoLiq);
		order.setValorTotal(valorTot);
			
		order = orderRepository.save(order);
		
		return OrderMapper.toDTO(order);
	
	}
	
	
}
