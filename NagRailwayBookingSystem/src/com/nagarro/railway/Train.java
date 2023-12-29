package com.nagarro.railway;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rishabhsinghla Train entity containing coaches and displaying
 */

class Train {
	private String trainID;
	private List<Coach> coaches;

	public Train(String trainID) {
		this.trainID = trainID;
		this.coaches = new ArrayList<>();
		this.initializeCoaches();
	}

	private void initializeCoaches() {
		for (int i = 1; i <= 10; i++) {
			PricingStrategy pricingStrategy = new DynamicPricingStrategy(0.10, 0.12, 0.15, 0.18);
			Coach coach = new Coach("C" + i, 2000, pricingStrategy);
			coaches.add(coach);
		}
	}

	public void addCoach(Coach coach) {
		coaches.add(coach);
	}

	public List<Coach> getCoaches() {
		return coaches;
	}

	public void displayStatus() {
		System.out.println("Train ID: " + trainID);
	}

	public String getTrainID() {
		return trainID;
	}

	public Coach getCoach(String coachID) {
		for (Coach coach : coaches) {
			if (coach.getCoachID().equals(coachID)) {
				return coach;
			}
		}
		return null;
	}

	public Coach getDefaultCoach() {
		return coaches.get(0);
	}
}