package au.com.dius.shop.checkout.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.dius.shop.checkout.Product;

public class FreeBundleItemPricingRuleTest {

	@Test
	public void testApplyPricingRuleWhereCriteriaHasBeenMet() {
		// Given
		Product mbp = new Product("mbp", "MacBook Pro", 1399.99);
		Product vga = new Product("vga", "VGA adapter", 30.00);

		// free VGA adapter free of charge with every MacBook Pro sold
		PricingRule freeBundleItemPricingRule = new FreeBundleItemPricingRule(mbp, 1, vga);

		List<Product> items = new ArrayList<>();
		items.add(mbp);
		items.add(vga);

		// When
		Double discount = freeBundleItemPricingRule.apply(items);

		// Then
		assertEquals(new Double(30.0), discount);
	}

	@Test
	public void testApplyPricingRuleWithoutMeetingTheCriteria() {
		// Given
		Product mbp = new Product("mbp", "MacBook Pro", 1399.99);
		Product vga = new Product("vga", "VGA adapter", 30.00);

		// free VGA adapter free of charge with every MacBook Pro sold
		PricingRule freeBundleItemPricingRule = new FreeBundleItemPricingRule(mbp, 1, vga);

		List<Product> items = new ArrayList<>();
		items.add(vga);

		// When
		Double discount = freeBundleItemPricingRule.apply(items);

		// Then
		assertEquals(new Double(0.0), discount);
	}

}
