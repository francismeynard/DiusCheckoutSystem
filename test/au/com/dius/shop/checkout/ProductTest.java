package au.com.dius.shop.checkout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.dius.shop.checkout.Product;

public class ProductTest {

	@Test
	public void testCreateProduct() {
		Product ipad = new Product("ipd", "Super iPad", 549.99);

		assertEquals("ipd", ipad.getSku());
		assertEquals("Super iPad", ipad.getName());
		assertEquals(new Double(549.99), ipad.getPrice());
	}

	@Test
	public void testCompareProduct() {
		Product ipad1 = new Product("ipd", "Super iPad", 549.99);
		Product ipad2 = new Product("ipd", "Super iPad", 549.99);

		assertTrue(ipad1.equals(ipad2));
		assertFalse(ipad1.equals(new Product("mbp", "MacBook Pro", 1399.99)));
		assertFalse(ipad1.equals(new Object()));
		assertFalse(ipad1.equals(null));
		assertFalse(new Product(null, "test", 0.0).equals(ipad1));
	}

}
