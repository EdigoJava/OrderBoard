package com.silverbars.orderboard.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_TABLE")
public final class Order {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false,name ="USERID")
	private long userID;
	
	@Column(nullable =false,name= "QUANTITY")
	private  double orderQuantitiy;
	
	@Column(nullable =false,name="PRICE_PER_KG")
	private  BigDecimal pricePerKg;
	
	@Column(nullable =false,name="ORDER_TYPE")
	private  String orderType;
	
		

	public Order(long id, long userID, double orderQuantitiy, BigDecimal pricePerKg, String orderType) {
		super();
		this.id = id;
		this.userID = userID;
		this.orderQuantitiy = orderQuantitiy;
		this.pricePerKg = pricePerKg;
		this.orderType = orderType;
	}
		
	public Order() {
	}


	public Order(long userID, double orderQuantitiy, BigDecimal pricePerKg, String orderType) {
		super();
		this.userID = userID;
		this.orderQuantitiy = orderQuantitiy;
		this.pricePerKg = pricePerKg;
		this.orderType = orderType;
	}


	public long getId() {
		return id;
	}
	public double getOrderQuantitiy() {
		return orderQuantitiy;
	}
	public BigDecimal getPricePerKg() {
		return pricePerKg;
	}
	public String getOrderType() {
		return orderType;
	}
}
