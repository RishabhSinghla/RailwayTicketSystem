package com.nagarro.railway;

import java.util.concurrent.atomic.AtomicInteger;

import com.nagarro.railway.enums.MealPreference;

/**
 * @author rishabhsinghla user entity class
 */
class User {
	private String userID;
	private String name;
	private int age;
	private String gender;
	private String phone;
	private MealPreference mealPreference;
	private static final AtomicInteger userIDCounter = new AtomicInteger(0);

	public static int getGeneratedUserID() {
		return userIDCounter.incrementAndGet();
	}

	public User(String userID, String name, int age, String gender, String phone, MealPreference mealPreference) {
		this.userID = userID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.mealPreference = mealPreference;
	}

	public void displayDetails() {
		System.out.println("\nUser Details:");
		System.out.println("User ID: " + userID);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Gender: " + gender);
		System.out.println("Phone: " + phone);
		System.out.println("MealPreference: " + mealPreference);
	}
}