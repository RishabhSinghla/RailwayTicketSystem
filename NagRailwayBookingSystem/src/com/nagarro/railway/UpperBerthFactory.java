package com.nagarro.railway;

import com.nagarro.railway.enums.BerthType;

/**
 * @author rishabhsinghla creating seat at upper berth
 */
class UpperBerthFactory implements SeatFactory {
	@Override
	public Seat createSeat(String seatID, BerthType berthType, double basePrice) {
		return new Seat(seatID, berthType, basePrice);
	}
}