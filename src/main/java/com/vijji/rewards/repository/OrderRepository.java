package com.vijji.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijji.rewards.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByCustomerId(Long customerId);
}
