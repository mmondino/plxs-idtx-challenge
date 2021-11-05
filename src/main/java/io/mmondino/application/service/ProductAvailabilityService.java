package io.mmondino.application.service;

import io.mmondino.domain.model.Product;
import io.mmondino.domain.model.ProductSize;
import io.mmondino.infrastructure.data.loader.ProductCsvToListDataLoader;
import io.mmondino.infrastructure.data.loader.ProductSizeCsvToMapDataLoader;
import io.mmondino.infrastructure.data.loader.ProductSizeStockCsvToMapDataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductAvailabilityService {

    private List<Product> products;
    private Map<Integer, List<ProductSize>> productSizes;
    private Map<Integer, Integer> productSizeStocks;

    public ProductAvailabilityService(String productCsvFilePath, String sizeCsvFilePath, String stockCsvFilePath) throws IOException {
        products = new ProductCsvToListDataLoader().read(productCsvFilePath);
        productSizes = new ProductSizeCsvToMapDataLoader().read(sizeCsvFilePath);
        productSizeStocks = new ProductSizeStockCsvToMapDataLoader().read(stockCsvFilePath);
    }

    public ProductAvailabilityService(List<Product> products, Map<Integer, List<ProductSize>> productSizes, Map<Integer, Integer> productSizeStocks) {
        this.products = products;
        this.productSizes = productSizes;
        this.productSizeStocks = productSizeStocks;
    }

    public List<Integer> getAvailableProductsIds() {
        return products.stream()
                .filter(p -> this.satisfiesVisibilitySpecification(p))
                .sorted(Comparator.comparing(Product::getSequence))
                .map(p -> p.getId())
                .collect(Collectors.toList());
    }

    private Boolean satisfiesVisibilitySpecification(Product product) {
        if (this.hasAtLeastOneSpecialSize(product)) {
            return this.satisfiesProductWithSpecialSizeVisibilitySpecification(product);
        } else {
            return this.satisfiesProductWithNoSpecialSizeVisibilitySpecification(product);
        }
    }

    private Boolean satisfiesProductWithSpecialSizeVisibilitySpecification(Product product) {
        return this.hasAtLeastOneSpecialSizeWithBackSoonOrPositiveStock(product) &&
                this.hasAtLeastOneNoSpecialSizeWithBackSoonOrPositiveStock(product);
    }

    private Boolean satisfiesProductWithNoSpecialSizeVisibilitySpecification(Product product) {
        return this.hasAtLeastOneNoSpecialSizeWithBackSoonOrPositiveStock(product);
    }

    private Boolean hasAtLeastOneSpecialSize(Product product) {
        return productSizes.getOrDefault(product.getId(), new ArrayList<ProductSize>())
                .stream()
                .anyMatch(ProductSize::getSpecial);
    }

    private Boolean hasAtLeastOneSpecialSizeWithBackSoonOrPositiveStock(Product product) {
        return productSizes.getOrDefault(product.getId(), new ArrayList<ProductSize>())
                .stream()
                .anyMatch(p -> p.getSpecial() && (p.getBackSoon() || hasPositiveStock(p)));
    }

    private Boolean hasAtLeastOneNoSpecialSizeWithBackSoonOrPositiveStock(Product product) {
        return productSizes.getOrDefault(product.getId(), new ArrayList<ProductSize>())
                .stream()
                .anyMatch(p -> !p.getSpecial() && (p.getBackSoon() || hasPositiveStock(p)));
    }

    private Boolean hasPositiveStock(ProductSize productSize) {
        return productSizeStocks.getOrDefault(productSize.getId(), 0) > 0;
    }
}
