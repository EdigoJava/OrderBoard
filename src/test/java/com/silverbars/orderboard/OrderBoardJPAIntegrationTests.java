package com.silverbars.orderboard;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.repository.OrderRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class OrderBoardJPAIntegrationTests {
	
	@Autowired
	private TestEntityManager testEManager;
		
	@Autowired
	private OrderRepository repo;
	
	@Before
	public void setUp() {
		
		Order o1 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
		Order o2 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
		Order o3 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(700), "BUY");
		Order o4 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(300), "BUY");
		Order o5 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(100), "BUY");
		Order o6 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(200), "SELL");
		Order o7 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(200), "SELL");
		Order o8 = new Order(3, Double.valueOf(1), BigDecimal.valueOf(300), "SELL");
		
		testEManager.persist(o1);
		testEManager.persist(o2);
		testEManager.persist(o3);
		testEManager.persist(o4);
		testEManager.persist(o5);
		testEManager.persist(o6);
		testEManager.persist(o7);
		testEManager.persist(o8);
				
	}
	
	
	@Test
	public void testRegisterOrderCancelAndGetSummary() {

		Order o = new Order(35, Double.valueOf(1), BigDecimal.valueOf(200), "SELL");
		
		repo.save(o);
		
		List<Order> orders = repo.findAll();
				
		assertEquals(orders.size(), 9); // Persisted Row
		
		orders = null;
		
		orders = repo.findIdByOrderType("BUY");
		
		assertEquals(orders.size(), 5);//Total 5 row "BUY"
		
		orders = null;
		
		repo.deleteById(Long.valueOf(2));
		
		orders = repo.findAll();
		
		assertEquals(orders.size(), 8);//1 is deleted total 8 rows
								
	}

}
