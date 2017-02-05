package au.com.dius.shop.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.com.dius.shop.checkout.Checkout;
import au.com.dius.shop.checkout.rules.BulkDiscountPricingRule;
import au.com.dius.shop.checkout.rules.FreeBundleItemPricingRule;
import au.com.dius.shop.checkout.rules.ItemDealDiscountPricingRule;
import au.com.dius.shop.checkout.rules.PricingRule;

public class DiusCheckoutSystemApp {

	private List<PricingRule> pricingRules = new ArrayList<>();
	private Checkout checkout = new Checkout(pricingRules);

	public static void main(String[] args) {
		new DiusCheckoutSystemApp().start();
	}

	public void start() {
		setupPricingRules();

		// Scenario 1
		processCheckout("atv, atv, atv, vga");

		// Scenario 2
		processCheckout("atv, ipd, ipd, atv, ipd, ipd, ipd");

		// Scenario 3
		processCheckout("mbp, vga, ipd");
	}

	private void setupPricingRules() {
		// 3for2 deal - if you buy 3 Apple TVs, you will pay the price of 2 only
		pricingRules.add(new ItemDealDiscountPricingRule(checkout.findProduct("atv"), 3, 2));

		// price will drop to $499.99 each, if someone buys more than 4 iPad
		pricingRules.add(new BulkDiscountPricingRule(checkout.findProduct("ipd"), 4, 499.99));

		// free VGA adapter free of charge with every MacBook Pro sold
		pricingRules.add(new FreeBundleItemPricingRule(checkout.findProduct("mbp"), 1, checkout.findProduct("vga")));
	}

	private void processCheckout(String items) {
		checkout.clearItems();

		Arrays.asList(items.split(",")).forEach(item -> checkout.scan(item));

		System.out.println("\n---------------------------------\nSKUs Scanned: " + items);
		System.out.println("Total: " + checkout.total());
	}

}
