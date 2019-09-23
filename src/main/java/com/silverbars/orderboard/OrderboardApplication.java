package com.silverbars.orderboard;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.repository.OrderRepository;




@SpringBootApplication
public class OrderboardApplication {//implements CommandLineRunner{ --You can open these comments for testing.--
	
//	@Autowired
//	OrderRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderboardApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//				
//		Order o1 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
//		Order o2 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
//		Order o3 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(700), "BUY");
//		Order o4 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(300), "BUY");
//		Order o5 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
//		Order o6 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(200), "SELL");
//		Order o7 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(200), "SELL");
//		Order o8 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(300), "SELL");
//		
//		repo.save(o1);
//		repo.save(o2);
//		repo.save(o3);
//		repo.save(o4);
//		repo.save(o5);
//		repo.save(o6);
//		repo.save(o7);
//		repo.save(o8);
//		
//	}

}
