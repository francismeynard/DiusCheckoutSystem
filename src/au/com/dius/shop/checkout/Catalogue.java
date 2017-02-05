package au.com.dius.shop.checkout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Catalogue {

	private List<Product> products = new ArrayList<>();

	public void initialize(String filename) throws IOException {
		try {
			URL resource = ClassLoader.getSystemResource(filename);
			if (resource != null) {
				List<String> itemsInFile = Files.readAllLines(Paths.get(resource.toURI()));
				addProducts(itemsInFile);
			} else {
				throw new IOException("File not found!");
			}
		} catch (URISyntaxException uriSyntaxException) {
			throw new IOException(uriSyntaxException);
		}
	}

	private void addProducts(List<String> items) {
		items.forEach(item -> {
			String[] details = item.split("\\|");
			products.add(new Product(details[0].trim(), details[1].trim(), Double.valueOf(details[2].trim())));
		});
	}

	public Product findProduct(String productSku) {
		return products.stream().filter(product -> product.getSku().equals(productSku)).findAny().orElse(null);
	}

	public List<Product> getAllProducts() {
		return products;
	}

}
