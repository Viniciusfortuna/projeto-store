package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.OrderDetails;
import com.example.store.pk.ItemSequence;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, ItemSequence> {

}
