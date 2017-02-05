package au.com.dius.shop.checkout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.dius.shop.checkout.rules.BulkDiscountPricingRule;
import au.com.dius.shop.checkout.rules.FreeBundleItemPricingRule;
import au.com.dius.shop.checkout.rules.ItemDealDiscountPricingRule;
import au.com.dius.shop.checkout.rules.PricingRule;

public class CheckoutTest {

	@Test
	public void canScanItems() {
		// Given
		Checkout checkout = new Checkout(new ArrayList<>());

		// When
		checkout.scan("ipd");
		checkout.scan("mbp");
		checkout.scan("atv");
		checkout.scan("vga");

		// Then
		assertEquals(4, checkout.getItemCount());
	}

	@Test
	public void testScanGivenInvalidItems() {
		// Given
		Checkout checkout = new Checkout(new ArrayList<>());

		// When
		checkout.scan("ipd");
		checkout.scan("mbp");
		checkout.scan("inv"); // invalid
		checkout.scan("ukn"); // invalid

		// Then
		assertEquals(2, checkout.getItemCount());
	}

	@Test
	public void testCheckoutWithoutAnyPricingRules() {
		// Given
		List<PricingRule> pricingRules = new ArrayList<>();
		Checkout checkout = new Checkout(pricingRules);

		// When
		checkout.scan("ipd");
		checkout.scan("mbp");
		checkout.scan("atv");
		checkout.scan("vga");
		Double total = checkout.total();

		// Then
		assertEquals(new Double(2089.48), total);
	}

	@Test
	public void testCheckoutApplyingProductDealDiscountPricingRule() {
		// Given
		List<PricingRule> pricingRules = new ArrayList<>();
		Checkout checkout = new Checkout(pricingRules);

		// 3for2 deal - if you buy 3 Apple TVs, you will pay the price of 2 only
		pricingRules.add(new ItemDealDiscountPricingRule(checkout.findProduct("atv"), 3, 2));

		// When
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("vga");
		Double total = checkout.total();

		// Then
		assertEquals(new Double(249.00), total);
	}

	@Test
	public void testCheckoutApplyingBulkDiscountPricingRule() {
		// Given
		List<PricingRule> pricingRules = new ArrayList<>();
		Checkout checkout = new Checkout(pricingRules);

		// price will drop to $499.99 each, if someone buys more than 4 iPad
		pricingRules.add(new BulkDiscountPricingRule(checkout.findProduct("ipd"), 4, 499.99));

		// When
		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");
		Double total = checkout.total();

		// Then
		assertEquals(new Double(2718.95), total);
	}

	@Test
	public void testCheckoutApplyingFreeBundleProductPricingRule() {
		// Given
		List<PricingRule> pricingRules = new ArrayList<>();
		Checkout checkout = new Checkout(pricingRules);

		// free VGA adapter free of charge with every MacBook Pro sold
		pricingRules.add(new FreeBundleItemPricingRule(checkout.findProduct("mbp"), 1, checkout.findProduct("vga")));

		// When
		checkout.scan("mbp");
		checkout.scan("vga");
		checkout.scan("ipd");
		Double total = checkout.total();

		// Then
		assertEquals(3, checkout.getItemCount());
		assertEquals(new Double(1949.98), total);
	}

	@Test
	public void testCheckoutApplyingAllPricingRules() {
		// Given
		List<PricingRule> pricingRules = new ArrayList<>();
		Checkout checkout = new Checkout(pricingRules);

		pricingRules.add(new ItemDealDiscountPricingRule(checkout.findProduct("atv"), 3, 2));
		pricingRules.add(new BulkDiscountPricingRule(checkout.findProduct("ipd"), 4, 499.99));
		pricingRules.add(new FreeBundleItemPricingRule(checkout.findProduct("mbp"), 1, checkout.findProduct("vga")));

		// When
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("atv");

		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");

		checkout.scan("mbp");
		checkout.scan("vga");
		Double total = checkout.total();

		// Then
		assertEquals(10, checkout.getItemCount());
		assertEquals(new Double(4118.94), total);
	}

}
