package com.nagarro.railway;

/**
 * @author rishabhsinghla class for calculating dynamic price for seat
 */
public class DynamicPricingStrategy implements PricingStrategy {

	private final double increment20;
	private final double increment30;
	private final double increment35;
	private final double increment40;

	public DynamicPricingStrategy(double increment20, double increment30, double increment35, double increment40) {
		this.increment20 = increment20;
		this.increment30 = increment30;
		this.increment35 = increment35;
		this.increment40 = increment40;
	}

	@Override
	public double calculatePrice(double basePrice, int fillingPercentage) {
		if (fillingPercentage < 20) {
			return basePrice;
		} else if (fillingPercentage < 30) {
			return basePrice + (increment20 * basePrice);
		} else if (fillingPercentage < 35) {
			return basePrice + (increment30 * basePrice);
		} else if (fillingPercentage < 40) {
			return basePrice + (increment35 * basePrice);
		} else {
			return basePrice + (increment40 * basePrice);
		}
	}
}