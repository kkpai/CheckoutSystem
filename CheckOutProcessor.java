package com.checkout;

import java.text.NumberFormat;
import java.util.Locale;

class CheckOutProcessor {
	
	String checkOutWithoutDiscount(Fruit[] fruits) throws InvalidFruitException {
		double totalPrice = 0;
		if (fruits != null) {
			for (Fruit fruit:fruits) {
				validateFruit(fruit);
				totalPrice = totalPrice + fruit.getPrice();
			}		
		}
		return formatPriceByCurrency(totalPrice);
	}
	
	String checkOutWithDiscount(Fruit[] fruits) throws InvalidFruitException {
		double totalPrice = 0;
		int totalApples = 0;
		int totalOranges = 0;
		if (fruits != null) {
			for (Fruit fruit:fruits) {
				validateFruit(fruit);
				if (fruit.equals(Fruit.APPLE)) {
					totalApples++;
				}
				if (fruit.equals(Fruit.ORANGE)) {
					totalOranges++;
				}				
			}		
		}
		
		int nonDiscountedApples = getNonDiscountedApples(totalApples);
		int discountedApples = getDiscountedFruits(totalApples, nonDiscountedApples);
		double priceOfNonDiscountedApples = getPriceOfNonDiscountedFruits(nonDiscountedApples, Fruit.APPLE);
		double priceOfDiscountedApples = getPriceOfDiscountedApples(discountedApples);
		
		int nonDiscountedOranges = getNonDiscountedOranges(totalOranges);
		int discountedOranges = getDiscountedFruits(totalOranges, nonDiscountedOranges);
		double priceOfNonDiscountedOranges = getPriceOfNonDiscountedFruits(nonDiscountedApples, Fruit.ORANGE);
		double priceOfDiscountedOranges = getPriceOfDiscountedOranges(discountedOranges);
		
		totalPrice = priceOfDiscountedApples + priceOfNonDiscountedApples + priceOfDiscountedOranges + priceOfNonDiscountedOranges;
		
		return formatPriceByCurrency(totalPrice);
	}

	double getPriceOfDiscountedOranges(int discountedOranges) {
		return discountedOranges/3 * Fruit.ORANGE.getPrice() * 2;
	}

	double getPriceOfDiscountedApples(int discountedApples) {
		return discountedApples/2 * Fruit.APPLE.getPrice();
	}

	double getPriceOfNonDiscountedFruits(int nonDiscountedApples, Fruit fruit) {
		return nonDiscountedApples * fruit.getPrice();
	}

	int getNonDiscountedOranges(int totalOranges) {
		return totalOranges % 3;
	}

	int getDiscountedFruits(int totalFruits, int nonDiscountedFruits) {
		return totalFruits - nonDiscountedFruits;
	}

	int getNonDiscountedApples(int totalApples) {
		return totalApples % 2;
	}
	
	private void validateFruit(Fruit fruit) throws InvalidFruitException {
		if (fruit == null) {
			throw new InvalidFruitException();
		}		
	} 

	private String formatPriceByCurrency(double price) {
		NumberFormat uk = NumberFormat.getCurrencyInstance(Locale.UK);
		return uk.format(price);
	} 
}
