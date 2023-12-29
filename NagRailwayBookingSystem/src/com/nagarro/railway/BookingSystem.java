package com.nagarro.railway;

/**
 * @author rishabhsinghla Booking system class to book ticket
 */

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
	private static BookingSystem instance;
	private List<Train> trains;

	private BookingSystem() {
		this.trains = new ArrayList<>();
		initializeTrains();
	}

	public static synchronized BookingSystem getInstance() {
		if (instance == null) {
			instance = new BookingSystem();
		}
		return instance;
	}

	private void initializeTrains() {
		for (int i = 1; i <= 2; i++) {
			Train train = new Train("T" + i);
			trains.add(train);
		}
	}

	public List<Train> getTrains() {
		return trains;
	}

	public void displayTrainStatus() {
		if (trains.isEmpty()) {
			System.out.println("No trains available.");
		} else {
			System.out.println("Train Status:");
			for (Train train : trains) {
				train.displayStatus();
			}
		}
	}

	public void bookTicket(Train train, Coach coach, Seat seat, User user) {
		coach.addSeat(seat);
		coach.calculateDynamicPrice();
		user.displayDetails();
		this.displayTicketDetails(train, coach, seat);
	}

	private void displayTicketDetails(Train train, Coach coach, Seat seat) {
		System.out.println("\nTicket Details:");
		System.out.println("Train ID: " + train.getTrainID());
		System.out.println("Coach ID: " + coach.getCoachID());
		System.out.println("Seat ID: " + seat.getSeatID());
		System.out.println("Berth Type: " + seat.getBerthType());
		System.out.println("Price: " + seat.getPrice());
	}

}
