package io.mmondino.infrastructure.data.loader;

import io.mmondino.domain.model.Product;
import io.mmondino.infrastructure.data.loader.base.CsvToListDataLoader;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductCsvToListDataLoader extends CsvToListDataLoader<Product, List<Product>> {

    @Override
    protected Product mapFieldsToItem(List<String> fields) {
        return new Product(Integer.valueOf(fields.get(0)), Integer.valueOf(fields.get(1)));
    }

    @Override
    protected Collector<Product, ?, List<Product>> getCollector() {
        return Collectors.toList();
    }
}