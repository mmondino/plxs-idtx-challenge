package io.mmondino;

import io.mmondino.application.service.ProductAvailabilityService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = App.class.getClassLoader();

        ProductAvailabilityService productAvailabilityService = new ProductAvailabilityService(
                classLoader.getResource("product.csv").getPath(),
                classLoader.getResource("size.csv").getPath(),
                classLoader.getResource("stock.csv").getPath());

        List<Integer> productsIds = productAvailabilityService.getAvailableProductsIds();

        String output = productsIds.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));

        System.out.println(output);
    }
}
