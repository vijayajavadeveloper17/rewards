package com.vijji.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijji.rewards.model.RewardPoints;
import com.vijji.rewards.service.RewardsService;

@RestController
@RequestMapping("/api/v1")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@GetMapping("/rewards/{customerId}")
	public ResponseEntity<RewardPoints> getRewardPoints(@PathVariable Long customerId) {
		RewardPoints rewardPoints = rewardsService.getRewardPoints(customerId);
		return new ResponseEntity<RewardPoints>(rewardPoints, HttpStatus.OK);
	}

}
