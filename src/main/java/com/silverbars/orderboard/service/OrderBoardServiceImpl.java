package com.silverbars.orderboard.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.model.OrderSummary;
import com.silverbars.orderboard.repository.OrderRepository;

@Service
public class OrderBoardServiceImpl implements OrderBoardService {
	
	private final OrderRepository repo;
	
	@Autowired
	public OrderBoardServiceImpl(OrderRepository repo) {
		this.repo = repo;	
	}

	@Override
	public void registerOrder(Order newOrder) {
		repo.save(newOrder);
			}

	@Override
	public void cancelOrder(long orderId) {
		repo.deleteById(orderId);

	}

	@Override
	public List<OrderSummary> summaryOrders(String orderType) {
				
		List<OrderSummary> orderList = new ArrayList<>();
						
		Map<BigDecimal, Double> priceMap = repo.findIdByOrderType(orderType).stream()
				  .collect(Collectors.groupingBy(Order::getPricePerKg, Collectors.summingDouble(Order::getOrderQuantitiy)));
				
		priceMap.forEach((k, v) -> {
			
			orderList.add(new OrderSummary(v, k));
			
		});
		
		if(orderType.equalsIgnoreCase("SELL"))
			orderList.sort(Comparator.comparing(OrderSummary::getPricePerKg));
		else
			orderList.sort(Comparator.comparing(OrderSummary::getPricePerKg).reversed());
								
		return orderList;
	}
		
}
