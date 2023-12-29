package com.nagarro.railway;

import java.util.List;
import java.util.Scanner;

import com.nagarro.railway.enums.BerthType;
import com.nagarro.railway.enums.MealPreference;

/**
 * @author rishabhsinghla Main class for booking train tickets
 */

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			do {
				bookTicket(scanner);
				System.out.print("Do you want to book another ticket? (yes/no): ");
			} while ("yes".equalsIgnoreCase(scanner.nextLine().trim()));
		} finally {
			scanner.close();
		}
	}

	private static void bookTicket(Scanner scanner) {
		BookingSystem bookingSystem = BookingSystem.getInstance();

		System.out.println("Available Trains:");
		bookingSystem.displayTrainStatus();

		Train selectedTrain = selectTrain(scanner, bookingSystem);

		if (selectedTrain != null) {
			System.out.println("Train selected: " + selectedTrain.getTrainID());

			Coach selectedCoach = selectedTrain.getDefaultCoach();

			System.out.println("Available Berths: " + Constants.UPPER_BERTH + ", " + Constants.LOWER_BERTH + ", "
					+ Constants.MIDDLE_BERTH);
			BerthType selectedBerth = selectBerthType(scanner);

			SeatFactory seatFactory = createSeatFactory(selectedBerth.name());
			Seat selectedSeat = seatFactory.createSeat("S1", selectedBerth, 2000);

			System.out.println("Meal Preferences: " + Constants.VEG_MEAL + ", " + Constants.NON_VEG_MEAL);
			MealPreference selectedMealPreference = selectMealPreference(scanner);

			double calculatedPrice = selectedSeat.getPrice();
			System.out.println("Calculated Price: " + calculatedPrice);

			User user = getUserDetails(scanner, selectedMealPreference);

			System.out.println("Booking in progress. Please wait...");

			TimerService timerService = TimerService.getInstance();
			timerService.startTimer(Constants.WAIT_TIME_SECONDS, () -> {
				System.out.println("\nBooking Details:");
				try {
					bookingSystem.bookTicket(selectedTrain, selectedCoach, selectedSeat, user);
					System.out.println("\nTicket booked successfully!");
				} catch (Exception e) {
					System.out.println("Error during booking: " + e.getMessage());
				}
			});
		} else {
			System.out.println("Invalid train selection.");
		}
	}

	private static Train selectTrain(Scanner scanner, BookingSystem bookingSystem) {
		while (true) {
			try {
				System.out.print("Select a train by entering its ID: ");
				String selectedTrainID = scanner.nextLine();

				Train selectedTrain = findTrain(selectedTrainID, bookingSystem.getTrains());

				if (selectedTrain != null) {
					return selectedTrain;
				} else {
					throw new IllegalArgumentException("Invalid train selection.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + " Please try again.");
			}
		}
	}

	private static BerthType selectBerthType(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Enter your berth preference: ");
				String selectedBerth = scanner.nextLine().toUpperCase();
				return BerthType.valueOf(selectedBerth);
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage() + " Please try again.");
			}
		}
	}

	private static MealPreference selectMealPreference(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Enter your meal preference: ");
				String selectedMeal = scanner.nextLine().toUpperCase();
				return MealPreference.valueOf(selectedMeal);
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage() + " Please try again.");
			}
		}
	}

	private static User getUserDetails(Scanner scanner, MealPreference selectedMealPreference) {
		System.out.println("Enter your user details:");

		String userID = "U" + User.getGeneratedUserID();

		while (true) {
			try {
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();

				System.out.print("Enter Age: ");
				int age = Integer.parseInt(scanner.nextLine());

				System.out.print("Enter Gender: ");
				String gender = scanner.nextLine();

				System.out.print("Enter Phone: ");
				String phone = scanner.nextLine();

				return new User(userID, name, age, gender, phone, selectedMealPreference);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + " Please try again.");
			}
		}
	}

	private static Train findTrain(String trainID, List<Train> trains) {
		for (Train train : trains) {
			if (train.getTrainID().equals(trainID)) {
				return train;
			}
		}
		return null;
	}

	private static SeatFactory createSeatFactory(String selectedBerth) {
		switch (BerthType.valueOf(selectedBerth)) {
		case UPPER:
			return new UpperBerthFactory();
		case LOWER:
			return new LowerBerthFactory();
		case MIDDLE:
			return new MiddleBerthFactory();
		default:
			throw new IllegalArgumentException("Invalid berth type");
		}
	}
}