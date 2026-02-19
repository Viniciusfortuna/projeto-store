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
		order.setPesoBruto(dto.pesoBruto());
		order.setPesoLiquido(dto.pesoLiquido());
		order.setValorTotal(dto.valorTotal());
		
		order = orderRepository.save(order);
		
		if(dto.items() != null) {
			
			long cont =  1;
			
			for(OrderDetailsRequestDTO dtoDetails : dto.items()) {
				
				OrderDetails orderDetails = new OrderDetails();
			
				ItemSequence pk = new ItemSequence();
				pk.setOrder_id(order.getId());
				pk.setOrder_sequence(cont);
				orderDetails.setItemSequence(pk);
				
				Product product = productService.listarPorIdEntity(dtoDetails.productId());
				orderDetails.setProductId(product);
				
				orderDetails.setPrecoUnitario(dtoDetails.precoUnitario());
				orderDetails.setQuantidade(dtoDetails.quantidade());
				orderDetails.setValorTotal(dtoDetails.valorTotal());
				
				order.getItems().add(orderDetails);
				
				cont++;
			}
		}
		
		return OrderMapper.toDTO(order);
	
	}
	
	
}
