package au.com.dius.shop.testscenario;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.com.dius.shop.checkout.Checkout;
import au.com.dius.shop.checkout.rules.BulkDiscountPricingRule;
import au.com.dius.shop.checkout.rules.FreeBundleItemPricingRule;
import au.com.dius.shop.checkout.rules.ItemDealDiscountPricingRule;
import au.com.dius.shop.checkout.rules.PricingRule;

public class ExampleScenarioTest {

	private List<PricingRule> pricingRules = new ArrayList<>();

	private Checkout checkout = new Checkout(pricingRules);

	@Before
	public void setup() {
		// 3for2 deal - if you buy 3 Apple TVs, you will pay the price of 2 only
		pricingRules.add(new ItemDealDiscountPricingRule(checkout.findProduct("atv"), 3, 2));

		// price will drop to $499.99 each, if someone buys more than 4 iPad
		pricingRules.add(new BulkDiscountPricingRule(checkout.findProduct("ipd"), 4, 499.99));

		// free VGA adapter free of charge with every MacBook Pro sold
		pricingRules.add(new FreeBundleItemPricingRule(checkout.findProduct("mbp"), 1, checkout.findProduct("vga")));

		checkout.clearItems();
	}

	@Test
	public void testScenario1() {
		// SKUs Scanned: atv, atv, atv, vga
		// Total expected: $249.00

		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("vga");
		Double total = checkout.total();

		assertEquals(new Double(249.00), total);
	}

	@Test
	public void testScenario2() {
		// SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd
		// Total expected: $2718.95

		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");
		Double total = checkout.total();

		assertEquals(new Double(2718.95), total);
	}

	@Test
	public void testScenario3() {
		// SKUs Scanned: mbp, vga, ipd
		// Total expected: $1949.98

		checkout.scan("mbp");
		checkout.scan("vga");
		checkout.scan("ipd");

		assertEquals(new Double(1949.98), checkout.total());
	}

}
