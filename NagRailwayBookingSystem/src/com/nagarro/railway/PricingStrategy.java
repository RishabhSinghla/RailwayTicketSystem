package com.nagarro.railway;

/**
 * @author rishabhsinghla interface for pricing strategy
 */
interface PricingStrategy {
	double calculatePrice(double basePrice, int fillingPercentage);
}
