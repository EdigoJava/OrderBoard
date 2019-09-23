package com.silverbars.orderboard.service;

import java.util.List;


import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.model.OrderSummary;

public interface OrderBoardService {
	
	 public void registerOrder(Order newOrder);
	 
	 public void cancelOrder(long orderId);
	 
	 public List<OrderSummary> summaryOrders(String orderType);
	
}
