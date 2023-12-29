package com.nagarro.railway;

import com.nagarro.railway.enums.BerthType;

/**
 * @author rishabhsinghla class defining seat in the train
 */
class Seat {
	private String seatID;
	private BerthType berthType;
	private double price;

	public Seat(String seatID, BerthType berthType, double basePrice) {
		this.seatID = seatID;
		this.berthType = berthType;
		this.price = basePrice;
	}

	public double getPrice() {
		return price;
	}

	public String getSeatID() {
		return seatID;
	}

	public BerthType getBerthType() {
		return berthType;
	}
}