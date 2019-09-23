package com.silverbars.orderboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.silverbars.orderboard.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
	
	@Query("SELECT r FROM Order r where LOWER(r.orderType) = LOWER(:orderType)") 
    List<Order> findIdByOrderType(@Param("orderType") String orderType);
	

}
