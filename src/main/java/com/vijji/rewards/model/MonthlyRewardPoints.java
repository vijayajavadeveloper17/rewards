package com.vijji.rewards.model;

public class MonthlyRewardPoints {

	private int monthId;
	private Integer rewardPoints;
	private String monthName;

	public MonthlyRewardPoints(int monthId, String monthName, Integer rewardPoints) {
		super();
		this.monthId = monthId;
		this.monthName = monthName;
		this.rewardPoints = rewardPoints;
	}

	
	/**
	 * @return the monthId
	 */
	public int getMonthId() {
		return monthId;
	}

	/**
	 * @param monthId
	 *            the monthId to set
	 */
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	/**
	 * @return the rewardPoints
	 */
	public Integer getRewardPoints() {
		return rewardPoints;
	}

	/**
	 * @param rewardPoints
	 *            the rewardPoints to set
	 */
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}

	/**
	 * @param monthName
	 *            the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

}
