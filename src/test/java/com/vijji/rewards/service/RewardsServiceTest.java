package com.vijji.rewards.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.vijji.rewards.model.Customer;
import com.vijji.rewards.model.MonthlyRewardPoints;
import com.vijji.rewards.model.Order;
import com.vijji.rewards.model.RewardPoints;
import com.vijji.rewards.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class RewardsServiceTest {

	@InjectMocks
	RewardsService rewardsService;

	@Mock
	OrderRepository orderRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchRewardPoints() {
		List<Order> orders = getOrders();
		RewardPoints rewardPoints = new RewardPoints();
		rewardPoints.setTotalRewardPoints(10);
		MonthlyRewardPoints monthlyRewardPoints = new MonthlyRewardPoints(1,"jan", 20);
		List<MonthlyRewardPoints> list = new ArrayList<>();
		list.add(monthlyRewardPoints);
		rewardPoints.setMonthlyRewardPoints(list);
		when(orderRepository.findByCustomerId(Mockito.anyLong())).thenReturn(orders);
		RewardPoints rewardPointsResult = rewardsService.getRewardPoints(1L);
		assertEquals(rewardPoints.getTotalRewardPoints(),rewardPointsResult.getTotalRewardPoints());
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("david");
		return customer;
	}

	private List<Order> getOrders() {
		Customer customer = getCustomer();
		List<Order> orders = new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			Order order = new Order();
			order.setCustomer(customer);
			order.setId((long) i);
			order.setOrderDate(new Date());
			order.setOrderPrice((double) (i * 20));
		}
		return orders;

	}
}
