package com.silverbars.orderboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.silverbars.orderboard.entity.Order;
import com.silverbars.orderboard.model.OrderSummary;
import com.silverbars.orderboard.repository.OrderRepository;
import com.silverbars.orderboard.service.OrderBoardServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderboardApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	OrderBoardServiceImpl mocService;
	
	@Mock
	OrderRepository mockRepo;
	
	@InjectMocks 	
	OrderBoardServiceImpl impl;
	
	
	
	List<OrderSummary> orders;
	
	List<Order> orderListBuy;
	
	List<Order> orderListSell;
	
	JSONObject jo;
	
	JSONObject joOrderTypeNull;
	
	JSONObject joPriceNull;
	
	JSONObject joQuantityNull;
	
	@Before
	public void setUp() {
		
		orders = new ArrayList<OrderSummary>();
		
		orders.add(new OrderSummary());
		orders.add(new OrderSummary());
		
		orderListBuy = new ArrayList<Order>();
		
		orderListSell = new ArrayList<Order>();
				
		orderListBuy.add(new Order(12, Double.valueOf(10), BigDecimal.valueOf(180), "BUY"));
		orderListBuy.add(new Order(13, Double.valueOf(10), BigDecimal.valueOf(180), "BUY"));
		orderListBuy.add(new Order(14, Double.valueOf(10), BigDecimal.valueOf(200), "BUY"));
		orderListSell.add(new Order(15, Double.valueOf(10), BigDecimal.valueOf(180), "SELL"));
		orderListSell.add(new Order(16, Double.valueOf(10), BigDecimal.valueOf(180), "SELL"));
		orderListSell.add(new Order(17, Double.valueOf(10), BigDecimal.valueOf(200), "SELL"));
		
		
		jo = new JSONObject();
		joOrderTypeNull = new JSONObject();
		joPriceNull = new JSONObject();		
		joQuantityNull = new JSONObject();
		
		try {
			jo.put("orderQuantitiy", "50");
			jo.put("pricePerKg", "100");
			jo.put("orderType", "SELL");
			
			joOrderTypeNull.put("orderQuantitiy", "50");
			joOrderTypeNull.put("pricePerKg", "100");
			
			joPriceNull.put("orderQuantitiy", "50");			
			joPriceNull.put("orderType", "SELL");
			
			
			joQuantityNull.put("pricePerKg", "100");
			joQuantityNull.put("orderType", "SELL");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
    @Test	
	public void summaryOrdersWithElementsTest() {
		
    	try {
    		
    		when(mocService.summaryOrders("BUY")).thenReturn(orders);
    		
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/BUY")
					.accept(MediaType.APPLICATION_JSON)).andReturn();
			
			   int status = result.getResponse().getStatus();
			   assertEquals(200, status);
			   
		   	when(mocService.summaryOrders("SELL")).thenReturn(orders);
	    		
			result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/SELL")
						.accept(MediaType.APPLICATION_JSON)).andReturn();
				
				    status = result.getResponse().getStatus();
				   assertEquals(200, status);
				   
				   
		   when(mocService.summaryOrders("SELLSF")).thenReturn(orders);
		    		
				result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/SELLSF")
								.accept(MediaType.APPLICATION_JSON)).andReturn();
						
				 status = result.getResponse().getStatus();
				 assertEquals(400, status);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}
    
    @Test	
	public void summaryOrdersWithNoElementsTest() {
		
    	try {
    		   		
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/BUY")
					.accept(MediaType.APPLICATION_JSON)).andReturn();
						 
			assertEquals(true,result.getResponse().getContentAsString().startsWith("There is"));
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}
    
    @Test
    public void registerOrder() {
    	   	
    	try {
    		    		
  		  mockMvc.perform(MockMvcRequestBuilders.post("/orders/register")
  				.contentType(MediaType.APPLICATION_JSON)
  				.content(jo.toString()))
  				.andDo(print())
  				.andExpect(status().is2xxSuccessful());
  		  
  		  mockMvc.perform(MockMvcRequestBuilders.post("/orders/register")
  				.contentType(MediaType.APPLICATION_JSON)
  				.content(joOrderTypeNull.toString()))
  				.andDo(print())
  				.andExpect(status().isBadRequest());
  		  
  		  mockMvc.perform(MockMvcRequestBuilders.post("/orders/register")
  				.contentType(MediaType.APPLICATION_JSON)
  				.content(joPriceNull.toString()))
  				.andDo(print())
  				.andExpect(status().isBadRequest());
  		  
  		  mockMvc.perform(MockMvcRequestBuilders.post("/orders/register")
  				.contentType(MediaType.APPLICATION_JSON)
  				.content(joQuantityNull.toString()))
  				.andDo(print())
  				.andExpect(status().isBadRequest());
  		  			
		} catch (Exception e) {
			
			e.printStackTrace();
		}   	
    }
    
    @Test
    public void cancelOrder() {
    	
    	
		  try {
			  mockMvc.perform(MockMvcRequestBuilders.delete("/orders/cancel/2")
		  				.contentType(MediaType.APPLICATION_JSON)
		  				.content(joQuantityNull.toString()))
		  				.andDo(print())
		  				.andExpect(status().is2xxSuccessful());
			            
			            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void cancelOrderServerError() {
    	
    	
		  try {
			  			  
			  doThrow(new EmptyResultDataAccessException(2)).when(mocService).cancelOrder(2);
			  
			  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/orders/cancel/2")
		  				.contentType(MediaType.APPLICATION_JSON)
		  				.content(joQuantityNull.toString()))
		  				.andReturn();
			  
			  assertEquals(true,result.getResponse().getContentAsString().startsWith("There is no such order"));
			            
			            
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }
    
    @Test
    public void cancelOrderServerNoSuchElement() {
    	    	
		  try {
			  
			  doThrow(new NullPointerException()).when(mocService).cancelOrder(2);
			  
			  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/orders/cancel/2")
		  				.contentType(MediaType.APPLICATION_JSON)
		  				.content(joQuantityNull.toString()))
		  				.andReturn();
			            
			  assertEquals(true,result.getResponse().getContentAsString().startsWith("There is a server error"));
			  
			            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    @Test
    public void testServiceLayerSell() {

		when(mockRepo.findIdByOrderType("SELL")).thenReturn(orderListSell);
		
		List <OrderSummary> resultList = impl.summaryOrders("SELL");
				
		assertEquals(Double.valueOf(resultList.get(0).getOrderQuantity()).toString(),"20.0");
		   	
    }
    
    
    @Test
    public void testServiceLayerBuy() {

		when(mockRepo.findIdByOrderType("BUY")).thenReturn(orderListBuy);
		
		List <OrderSummary> resultList = impl.summaryOrders("BUY");
				
		assertEquals(Double.valueOf(resultList.get(0).getOrderQuantity()).toString(),"10.0");
		   	
    }

}
