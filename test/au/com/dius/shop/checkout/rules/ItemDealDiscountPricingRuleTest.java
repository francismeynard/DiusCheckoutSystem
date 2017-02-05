package au.com.dius.shop.checkout.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.dius.shop.checkout.Product;

public class ItemDealDiscountPricingRuleTest {

	@Test
	public void testApplyPricingRuleWhereCriteriaHasBeenMet() {
		// Given
		Product atv = new Product("atv", "Apple TV", 109.50);

		// 3for2 deal - if you buy 3 Apple TVs, you will pay the price of 2 only
		PricingRule itemDealDiscountPricingRule = new ItemDealDiscountPricingRule(atv, 3, 2);

		List<Product> items = new ArrayList<>();
		items.add(atv);
		items.add(atv);
		items.add(atv);

		// When
		Double discount = itemDealDiscountPricingRule.apply(items);

		// Then
		assertEquals(new Double(109.50), discount);
	}

	@Test
	public void testApplyPricingRuleWithoutMeetingTheCriteria() {
		// Given
		Product atv = new Product("atv", "Apple TV", 109.50);

		// 3for2 deal - if you buy 3 Apple TVs, you will pay the price of 2 only
		PricingRule itemDealDiscountPricingRule = new ItemDealDiscountPricingRule(atv, 3, 2);

		List<Product> items = new ArrayList<>();
		items.add(atv);
		items.add(atv);

		// When
		Double discount = itemDealDiscountPricingRule.apply(items);

		// Then
		assertEquals(new Double(0.0), discount);
	}

}
