package au.com.dius.shop.checkout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.dius.shop.checkout.rules.PricingRule;

public class Checkout {

	private static final String CATALOGUE_FILENAME = "catalogue.psv";

	private List<PricingRule> pricingRules;

	private Catalogue catalogue = new Catalogue();
	private List<Product> items = new ArrayList<>();

	public Checkout(List<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
		initializeCatalogue();
	}

	private void initializeCatalogue() {
		try {
			catalogue.initialize(CATALOGUE_FILENAME);
		} catch (IOException ioException) {
			throw new RuntimeException("Unable to initialize catalogue", ioException);
		}
	}

	public void scan(String item) {
		Product product = findProduct(item.trim());
		if (product != null) {
			items.add(product);
		}
	}

	public Double total() {
		Double total = 0.0;
		for (Product product : items) {
			total += product.getPrice();
		}
		Double discount = 0.0;
		for (PricingRule pricingRule : pricingRules) {
			discount += pricingRule.apply(items);
		}
		return total - discount;
	}

	public void clearItems() {
		items.clear();
	}

	public int getItemCount() {
		return items.size();
	}

	public Product findProduct(String item) {
		return catalogue.findProduct(item);
	}

}
