package com.nagarro.railway;

import com.nagarro.railway.enums.BerthType;

/**
 * @author rishabhsinghla interface for seatFactory
 */
interface SeatFactory {
	Seat createSeat(String seatID, BerthType berthType, double basePrice);
}