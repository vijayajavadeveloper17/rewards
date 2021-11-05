package com.vijji.rewards.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vijji.rewards.model.Customer;
import com.vijji.rewards.model.Order;
import com.vijji.rewards.model.RewardPoints;
import com.vijji.rewards.service.RewardsService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private RewardsService rewardsService;

	@BeforeEach
	public void setup() {
	}

	@Test
	public void getRewardPoints() throws Exception {
		int id = 1;
		RewardPoints rewardPoints = new RewardPoints();
		rewardPoints.setTotalRewardPoints(20);
		when(rewardsService.getRewardPoints(Mockito.anyLong())).thenReturn(rewardPoints);
		mockMvc.perform(get("/api/v1/rewards/" + id))
		.andDo(print()).andExpect(jsonPath("$.*", hasSize(1)))
				.andExpect(jsonPath("$.totalRewardPoints").value(20))
				.andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/rewards/" + Integer.MAX_VALUE))
        .andDo(print())
        .andExpect(status().isNotFound());
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("david");
		return customer;
	}
	
	private List<Order> getOrders(){
		Customer customer = getCustomer();
		List<Order> orders = new ArrayList<>();
		for(int i = 1; i<3;i++) {
			Order order = new Order();
			order.setCustomer(customer);
			order.setId((long) i);
			order.setOrderDate(new Date());
			order.setOrderPrice((double) (i*20));
		}
		return orders;
				
	}
}
