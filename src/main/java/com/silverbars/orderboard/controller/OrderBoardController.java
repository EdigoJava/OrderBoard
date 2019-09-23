package com.silverbars.orderboard.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.model.OrderSummary;
import com.silverbars.orderboard.service.OrderBoardServiceImpl;

import com.silverbars.orderboard.validator.OrderBoardRequestValidator;


@RequestMapping("/orders")
@RestController
public class OrderBoardController {
	
	@Autowired
	private OrderBoardServiceImpl service;
	
	@Autowired
	private OrderBoardRequestValidator validator;
	
	@GetMapping("/{orderType}")
	public ResponseEntity<Object>  summaryOrders(@PathVariable(name="orderType")String orderType) {
		
		if (validator.validateOrderSummaryRequest(orderType)) {
			
			List<OrderSummary> orders = service.summaryOrders(orderType);
			
			if(orders == null) {
				
				return new ResponseEntity<Object>("There is a error occured!, please try again later...", HttpStatus.INTERNAL_SERVER_ERROR);
			}
									
			if(orders.size() == 0) {
				
				return new ResponseEntity<Object>("There is no orders", HttpStatus.OK);
			}
			
		
			return new ResponseEntity<Object>(orders, HttpStatus.OK);			
		}
						
		return new ResponseEntity<Object>("Wrong service endpoint...", HttpStatus.BAD_REQUEST);
	 }
	
	@PostMapping("/register")   
	public ResponseEntity<String> registerOrder(@RequestBody Order newOrder) {
		
		String result = validator.validateOrderObject(newOrder);
		
		if(result.equalsIgnoreCase("OK")) {
			
			 try {
					service.registerOrder(newOrder);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<String>("There is a server error occured!, please try again later...",HttpStatus.INTERNAL_SERVER_ERROR);					
				}
			 return new ResponseEntity<String>("Saved succesfully",HttpStatus.OK);
			
		}
			
		 return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
	 }
	
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<String> cancelOrderById(@PathVariable(name="id")Long id) {
				
		try {
			service.cancelOrder(id);
			
		} catch (Exception e) {
			
			if (e instanceof EmptyResultDataAccessException) {
				return new ResponseEntity<String>("There is no such order with "+id,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("There is a server error, please try again later... ",HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
			}
				
		return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
	}
}
