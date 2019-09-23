package com.silverbars.orderboard.model;

import java.math.BigDecimal;

public final class OrderSummary {
	
	private double orderQuantity;
	private BigDecimal pricePerKg;
		
	public OrderSummary() {
		super();
	}
	
	public OrderSummary(double orderQuantity, BigDecimal pricePerKg) {
		super();
		this.orderQuantity = orderQuantity;
		this.pricePerKg = pricePerKg;
	}
	
	public double getOrderQuantity() {
		return orderQuantity;
	}
	public BigDecimal getPricePerKg() {
		return pricePerKg;
	}		
}
