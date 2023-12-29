package com.nagarro.railway;

import com.nagarro.railway.enums.BerthType;

/**
 * @author rishabhsinghla for creating seat in lower berth
 */
class LowerBerthFactory implements SeatFactory {
	@Override
	public Seat createSeat(String seatID, BerthType berthType, double basePrice) {
		return new Seat(seatID, berthType, basePrice);
	}
}