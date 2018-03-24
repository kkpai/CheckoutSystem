package com.checkout;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckOutProcessorTest {
	
	private static final String TEST_PRICE_THREE_APPLES_FOUR_ORANGES_DISCOUNTED = "£1.95";
	private static final String TEST_PRICE_TWO_APPLES_THREE_ORANGES_DISCOUNTED = "£1.10";
	private static final String TEST_ZERO_TOTAL_PRICE = "£0.00";
	private static final String TEST_PRICE_WITHOUT_DISCOUNT = "£2.05";
	CheckOutProcessor checkOutProcessor;
	
	@Before
	public void setUp() throws Exception {
		checkOutProcessor = new CheckOutProcessor();
	}
	
	@Test
	public void testCheckOutForValidFruits() throws InvalidFruitException {
		Fruit[] fruits = {Fruit.APPLE, Fruit.APPLE, Fruit.ORANGE, Fruit.APPLE};
		String totalPrice = checkOutProcessor.checkOutWithoutDiscount(fruits);
		assertTrue(totalPrice.equals(TEST_PRICE_WITHOUT_DISCOUNT));
	}

	@Test
	public void testCheckOutWithDiscountForValidFruits() throws InvalidFruitException {
		Fruit[] fruits = {Fruit.APPLE, Fruit.APPLE, Fruit.ORANGE, Fruit.ORANGE, Fruit.ORANGE};
		String totalPrice = checkOutProcessor.checkOutWithDiscount(fruits);
		assertTrue(totalPrice.equals(TEST_PRICE_TWO_APPLES_THREE_ORANGES_DISCOUNTED));
	}
	
	@Test
	public void testCheckOutWithDiscountForValidFruits1() throws InvalidFruitException {
		Fruit[] fruits = {Fruit.APPLE, Fruit.APPLE, Fruit.APPLE, Fruit.ORANGE, Fruit.ORANGE, Fruit.ORANGE, Fruit.ORANGE};
		String totalPrice = checkOutProcessor.checkOutWithDiscount(fruits);
		assertTrue(totalPrice.equals(TEST_PRICE_THREE_APPLES_FOUR_ORANGES_DISCOUNTED));
	}	
	
	@Test
	public void testCheckOutForNullFruits() throws InvalidFruitException {
		Fruit[] fruits = null;
		String totalPrice = checkOutProcessor.checkOutWithoutDiscount(fruits);
		assertTrue(totalPrice.equals(TEST_ZERO_TOTAL_PRICE));
	}

	@Test
	public void testCheckOutForBlankFruits() throws InvalidFruitException {
		Fruit[] fruits = new Fruit[]{};
		String totalPrice = checkOutProcessor.checkOutWithoutDiscount(fruits);
		assertTrue(totalPrice.equals(TEST_ZERO_TOTAL_PRICE));
	}
	
	@Test
	public void testGetNonDiscountedApples()  {
		int totalApples = 3;
		int nonDiscountedApples = checkOutProcessor.getNonDiscountedApples(totalApples);
		assertTrue(nonDiscountedApples == 1);
	}
	
	@Test
	public void testGetNonDiscountedOranges()  {
		int totalOranges = 4;
		int nonDiscountedOranges = checkOutProcessor.getNonDiscountedOranges(totalOranges);
		assertTrue(nonDiscountedOranges == 1);
	}	
	
	@Test
	public void testGetDiscountedFruits()  {
		int totalFruits = 4;
		int nonDiscountedFruits = 1;
		int discountedFruits = checkOutProcessor.getDiscountedFruits(totalFruits, nonDiscountedFruits);
		assertTrue(discountedFruits == 3);
	}	
	
	@Test
	public void testGetPriceOfNonDiscountedFruits()  {
		Fruit fruit = Fruit.APPLE;
		int nonDiscountedApples = 1;
		double priceOfNonDiscountedFruits = checkOutProcessor.getPriceOfNonDiscountedFruits(nonDiscountedApples, fruit);
		assertTrue(priceOfNonDiscountedFruits == 0.60);
	}	
	
	@Test
	public void testGetPriceOfDiscountedApples()  {
		int discountedApples = 2;
		double priceOfDiscountedApples = checkOutProcessor.getPriceOfDiscountedApples(discountedApples );
		assertTrue(priceOfDiscountedApples == 0.60);
	}
	
	@Test
	public void testGetPriceOfDiscountedOranges()  {
		int discountedOranges = 3;
		double priceOfDiscountedOranges = checkOutProcessor.getPriceOfDiscountedOranges(discountedOranges);
		assertTrue(priceOfDiscountedOranges == 0.50);
	}
	
	@Test(expected = InvalidFruitException.class)
	public void testCheckOutForNullFruit() throws InvalidFruitException {
		Fruit[] fruits = {Fruit.APPLE, null, Fruit.ORANGE, Fruit.APPLE};
		checkOutProcessor.checkOutWithoutDiscount(fruits);
	}

	@After
	public void tearDown() throws Exception {
		checkOutProcessor = null;
	}
	
}
