package com.vijji.rewards.model;

import java.util.List;

public class RewardPoints {

	private List<MonthlyRewardPoints> monthlyRewardPoints;
	private int totalRewardPoints;

	public List<MonthlyRewardPoints> getMonthlyRewardPoints() {
		return monthlyRewardPoints;
	}

	public void setMonthlyRewardPoints(List<MonthlyRewardPoints> monthlyRewardPoints) {
		this.monthlyRewardPoints = monthlyRewardPoints;
	}

	public int getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(int totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

}
