package au.com.dius.shop.checkout.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.dius.shop.checkout.Product;

public class BulkDiscountPricingRuleTest {

	@Test
	public void testApplyPricingRuleWhereCriteriaHasBeenMet() {
		// Given
		Product ipd = new Product("ipd", "Super iPad", 549.99);

		// price will drop to $499.99 each, if someone buys more than 4 iPad
		PricingRule bulkDiscountPricingRule = new BulkDiscountPricingRule(ipd, 4, 499.99);

		List<Product> items = new ArrayList<>();
		items.add(ipd);
		items.add(ipd);
		items.add(ipd);
		items.add(ipd);
		items.add(ipd);

		// When
		Double discount = bulkDiscountPricingRule.apply(items);

		// Then
		assertEquals(new Double(250.0), discount);
	}

	@Test
	public void testApplyPricingRuleWithoutMeetingTheCriteria() {
		// Given
		Product ipd = new Product("ipd", "Super iPad", 549.99);

		// price will drop to $499.99 each, if someone buys more than 4 iPad
		PricingRule bulkDiscountPricingRule = new BulkDiscountPricingRule(ipd, 4, 499.99);

		List<Product> items = new ArrayList<>();
		items.add(ipd);
		items.add(ipd);
		items.add(ipd);
		items.add(ipd);

		// When
		Double discount = bulkDiscountPricingRule.apply(items);

		// Then
		assertEquals(new Double(0.0), discount);
	}

}
