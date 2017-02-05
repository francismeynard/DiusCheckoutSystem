package au.com.dius.shop.checkout.rules;

import java.util.List;

import au.com.dius.shop.checkout.Product;

public interface PricingRule {

	Double apply(List<Product> items);

}
