package com.vijji.rewards.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijji.rewards.model.MonthlyRewardPoints;
import com.vijji.rewards.model.Order;
import com.vijji.rewards.model.RewardPoints;
import com.vijji.rewards.repository.OrderRepository;

@Service
public class RewardsService {

	@Autowired
	OrderRepository orderRepository;
	
	public RewardPoints getRewardPoints(Long customerId) {
		RewardPoints rewardPoints = new RewardPoints();
		List<Order> ordersPerCustomer = orderRepository.findByCustomerId(customerId);
		TreeMap<Integer, Integer> monthWisePoints = new TreeMap<>();

		for (Order order : ordersPerCustomer) {
			Integer month = getMonthFromDate(order.getOrderDate());
			Integer points = monthWisePoints.getOrDefault(month, 0);
			double price = order.getOrderPrice();
			if (price > 100) {
				double temp = (price - 100);
				points += (2 * (int) temp);
				price = price - temp;

				// above 50
				points += 50;
			}

			if (price > 50 && price <= 100) {
				points += 1 * ((int) price - 50);
			}

			monthWisePoints.put(month, points);
		}

		List<MonthlyRewardPoints> rewardsPerMonth = new ArrayList<>();
		int totalRewards = 0;
		Set<Integer> monthIds = monthWisePoints.descendingKeySet();
		for (Integer monthId : monthIds) {
			totalRewards += monthWisePoints.get(monthId);
			MonthlyRewardPoints monthlyRewardPoints = new MonthlyRewardPoints(monthId, Month.of(monthId).name(), monthWisePoints.get(monthId));
			rewardsPerMonth.add(monthlyRewardPoints);
		}
		rewardPoints.setTotalRewardPoints(totalRewards);
		rewardPoints.setMonthlyRewardPoints(rewardsPerMonth);

		return rewardPoints;
	}

	private Integer getMonthFromDate(Date orderDate) {
		LocalDate localDate = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.getMonthValue();
	}
}
