package au.com.dius.shop.checkout.rules;

import java.util.List;

import au.com.dius.shop.checkout.Product;

/*
 * This rule states that an item will have a bulk discounted applied, 
 * where the price will drop to certain amount (discountedPrice) each, 
 * if someone buys more than the minimum number (minimumItemToBuy).
 */
public class BulkDiscountPricingRule implements PricingRule {

	private Product item;
	private int minimumItemToBuy;
	private Double discountedPrice;

	public BulkDiscountPricingRule(Product item, int minimumItemToBuy, Double discountedPrice) {
		this.item = item;
		this.minimumItemToBuy = minimumItemToBuy;
		this.discountedPrice = discountedPrice;
	}

	@Override
	public Double apply(List<Product> items) {
		long bulkItemCount = items.stream().filter(it -> it.equals(item)).count();

		Double discount = 0.0;
		if (bulkItemCount > minimumItemToBuy) {
			discount = bulkItemCount * (item.getPrice() - discountedPrice);
		}
		return discount;
	}

}
