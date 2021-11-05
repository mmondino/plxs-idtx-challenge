package io.mmondino.application.service;

import io.mmondino.domain.model.Product;
import io.mmondino.domain.model.ProductSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ProductAvailabilityServiceTest {

    @Test
    public void givenProductWithNoSpecialSizeMarkedAsBackSoon_shouldReturnProductId() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;

        List<Product> products = Arrays.asList(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, true, false)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 0);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(1, ids.size());
        Assertions.assertEquals(PRODUCT_ID, ids.get(0));
    }

    @Test
    public void givenProductWithNoSpecialSizeWithPositiveStock_shouldReturnProductId() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, false)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 10);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(1, ids.size());
        Assertions.assertEquals(PRODUCT_ID, ids.get(0));
    }

    @Test
    public void givenProductWithNoSpecialSizeWithoutPositiveStockAndNotMarkedAsBackSoon_shouldReturnEmptyList() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, false)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 0);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(0, ids.size());
    }

    @Test
    public void givenProductWithSpecialSizeWithoutNoSpecialSize_shouldReturnEmptyList() {

        final Integer PRODUCT_ID = 1;
        final Integer SPECIAL_PRODUCT_SIZE_ID = 2;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, true)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(SPECIAL_PRODUCT_SIZE_ID, 10);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(0, ids.size());
    }

    @Test
    public void givenProductWithSpecialSizeWithNoSpecialSizeWithoutPositiveStockAndNotMarkedAsBackSoon_shouldReturnEmptyList() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;
        final Integer SPECIAL_PRODUCT_SIZE_ID = 2;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, false),
                new ProductSize(SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, true)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 0);
        productSizeStocks.put(SPECIAL_PRODUCT_SIZE_ID, 10);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(0, ids.size());
    }

    @Test
    public void givenProductWithSpecialSizeWithNoSpecialSizeWithoutPositiveStockAndMarkedAsBackSoon_shouldReturnProductId() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;
        final Integer SPECIAL_PRODUCT_SIZE_ID = 2;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, true, false),
                new ProductSize(SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, true)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 0);
        productSizeStocks.put(SPECIAL_PRODUCT_SIZE_ID, 10);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(1, ids.size());
        Assertions.assertEquals(PRODUCT_ID, ids.get(0));
    }

    @Test
    public void givenProductWithSpecialSizeWithNoSpecialSizeWithPositiveStockAndNotMarkedAsBackSoon_shouldReturnProductId() {

        final Integer PRODUCT_ID = 1;
        final Integer NO_SPECIAL_PRODUCT_SIZE_ID = 1;
        final Integer SPECIAL_PRODUCT_SIZE_ID = 2;

        List<Product> products = new ArrayList<>();
        products.add(new Product(PRODUCT_ID, 1));

        Map<Integer, List<ProductSize>> productSizes = new HashMap<>();
        productSizes.put(PRODUCT_ID, Arrays.asList(
                new ProductSize(NO_SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, false),
                new ProductSize(SPECIAL_PRODUCT_SIZE_ID, PRODUCT_ID, false, true)));

        Map<Integer, Integer> productSizeStocks = new HashMap<>();
        productSizeStocks.put(NO_SPECIAL_PRODUCT_SIZE_ID, 10);
        productSizeStocks.put(SPECIAL_PRODUCT_SIZE_ID, 10);

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                products, productSizes, productSizeStocks);
        List<Integer> ids = productAvailabilityService.getAvailableProductsIds();

        Assertions.assertEquals(1, ids.size());
        Assertions.assertEquals(PRODUCT_ID, ids.get(0));
    }
}