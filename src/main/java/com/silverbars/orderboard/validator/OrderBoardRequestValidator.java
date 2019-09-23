package com.silverbars.orderboard.validator;


import org.springframework.stereotype.Component;

import com.silverbars.orderboard.entity.Order;

@Component
public class OrderBoardRequestValidator {
		
	public boolean validateOrderSummaryRequest(String orderType) {
		
		if(orderType.equalsIgnoreCase("BUY")) {
			
			return true;
			
		}
		else if(orderType.equalsIgnoreCase("SELL")) {
			return true;			
		}
				
		return false;
	}
	
	public String validateOrderObject(Order o) {
		
		if(o.getOrderQuantitiy()==0 ) {
			
			return "Order Quantity can not be 0";			
		}
		
	   if(o.getPricePerKg() == null ) {
			
			return "Price per Kg can not be blank";
		}
	   
	   if(o.getOrderType() == null ) {
			return "Order Type can not be blank";
		}
		
		return "OK";
	}

}
