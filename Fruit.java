package com.checkout;

enum Fruit {
	APPLE(0.60), ORANGE(0.25);
	
	private double price;
	
	Fruit(double price) {
		this.price = price;
	}
	
	double getPrice() {
		return price;
	}
}
