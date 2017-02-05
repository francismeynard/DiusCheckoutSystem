package au.com.dius.shop.checkout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

public class CatalogueTest {

	private Catalogue catalogue = new Catalogue();

	@Test
	public void testEmptyCatalogue() {
		assertEquals(0, catalogue.getAllProducts().size());
	}

	@Test
	public void testInitializeCatalogueGivenValidFile() throws IOException {
		catalogue.initialize("test-catalogue.psv");

		assertEquals(10, catalogue.getAllProducts().size());
	}

	@Test(expected = IOException.class)
	public void testInitializeCatalogueGivenAnInvalidFile() throws IOException {
		catalogue.initialize("filenotfound.psv");
	}

	@Test
	public void testFindProductGivenAnExistingItem() throws IOException {
		// Given
		catalogue.initialize("test-catalogue.psv");
		String existingItem = "ipd";

		// When
		Product product = catalogue.findProduct(existingItem);

		// Then
		assertNotNull(product);
		assertEquals("ipd", product.getSku());
		assertEquals("Super iPad", product.getName());
		assertEquals(new Double(549.99), product.getPrice());
	}

	@Test
	public void testFindProductGivenAnUnknownItem() throws IOException {
		// Given
		catalogue.initialize("test-catalogue.psv");
		String unknownItem = "unknown";

		// When
		Product product = catalogue.findProduct(unknownItem);

		// Then
		assertNull(product);
	}

}
