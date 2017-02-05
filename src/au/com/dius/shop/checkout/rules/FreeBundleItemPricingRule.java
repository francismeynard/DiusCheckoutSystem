package au.com.dius.shop.checkout.rules;

import java.util.List;

import au.com.dius.shop.checkout.Product;

/*
 * This rule states that when an item is sold - with certain count (itemCountToBuy), 
 * another item (freeBundleItem) will be bundled free of charge.
 */
public class FreeBundleItemPricingRule implements PricingRule {

	private Product item;
	private int itemCountToBuy;
	private Product freeBundleItem;

	public FreeBundleItemPricingRule(Product item, int itemCountToBuy, Product freeBundleItem) {
		this.item = item;
		this.itemCountToBuy = itemCountToBuy;
		this.freeBundleItem = freeBundleItem;
	}

	@Override
	public Double apply(List<Product> items) {
		int freeCount = (int) items.stream().filter(it -> it.equals(item)).count() / itemCountToBuy;

		int bundleItemCount = (int) items.stream().filter(it -> it.equals(freeBundleItem)).count();

		return freeBundleItem.getPrice() * Math.min(freeCount, bundleItemCount);
	}

}
