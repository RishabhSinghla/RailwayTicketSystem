package com.nagarro.railway;

/**
 * @author rishabhsinghla Coach class to describe coach details
 */

import java.util.ArrayList;
import java.util.List;

class Coach {
	private String coachID;
	private List<Seat> seats;
	private int fillingPercentage;
	private double basePrice;
	private List<CoachObserver> observers = new ArrayList<>();
	private PricingStrategy pricingStrategy;

	public Coach(String coachID, double basePrice, PricingStrategy pricingStrategy) {
		this.coachID = coachID;
		this.seats = new ArrayList<>();
		this.fillingPercentage = 0;
		this.basePrice = basePrice;
		this.pricingStrategy = pricingStrategy;
	}

	public void addObserver(CoachObserver observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		for (CoachObserver observer : observers) {
			observer.updateCoachStatus(this);
		}
	}

	public void addSeat(Seat seat) {
		seats.add(seat);
	}

	public void calculateDynamicPrice() {
		basePrice = pricingStrategy.calculatePrice(basePrice, fillingPercentage);
	}

	public void displayStatus() {
		System.out.println("Coach ID: " + coachID);
		for (Seat seat : seats) {
			System.out.println("Seat ID: " + seat.getSeatID() + ", Berth Type: " + seat.getBerthType() + ", Price: "
					+ seat.getPrice());
		}
	}

	public void setFillingPercentage(int fillingPercentage) {
		try {
			if (fillingPercentage < 0 || fillingPercentage > 100) {
				throw new IllegalArgumentException("Filling percentage must be between 0 and 100");
			}
			this.fillingPercentage = fillingPercentage;
			notifyObservers();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public double getBasePrice() {
		return basePrice;
	}

	public String getCoachID() {
		return coachID;
	}

	public int getFillingPercentage() {
		return fillingPercentage;
	}

	public List<Seat> getSeats() {
		return seats;
	}
}