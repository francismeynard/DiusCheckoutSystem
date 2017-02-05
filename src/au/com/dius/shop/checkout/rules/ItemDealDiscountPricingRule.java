package au.com.dius.shop.checkout.rules;

import java.util.List;

import au.com.dius.shop.checkout.Product;

/*
 * This rule states that there will be a deal discount on certain item. 
 * For example, if you buy 3 Apple TVs (itemCountToBuy), you will pay the price of 2 only (itemCountToPay).
 */
public class ItemDealDiscountPricingRule implements PricingRule {

	private Product item;
	private int itemCountToBuy;
	private int itemCountToPay;

	public ItemDealDiscountPricingRule(Product item, int itemCountToBuy, int itemCountToPay) {
		this.item = item;
		this.itemCountToBuy = itemCountToBuy;
		this.itemCountToPay = itemCountToPay;
	}

	@Override
	public Double apply(List<Product> items) {
		long itemCount = items.stream().filter(it -> it.equals(item)).count();
		long actualItemCountToPay = itemCountToPay * ((int) itemCount / itemCountToBuy);

		Double discount = 0.0;
		if (actualItemCountToPay > 0) {
			discount = item.getPrice() * (itemCount - actualItemCountToPay);
		}
		return discount;
	}

}
