package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
