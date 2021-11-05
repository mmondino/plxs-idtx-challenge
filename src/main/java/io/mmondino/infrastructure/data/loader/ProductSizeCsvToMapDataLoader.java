package io.mmondino.infrastructure.data.loader;

import io.mmondino.domain.model.ProductSize;
import io.mmondino.infrastructure.data.loader.base.CsvToMapDataLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductSizeCsvToMapDataLoader extends CsvToMapDataLoader<ProductSize, Integer, List<ProductSize>, Map<Integer, List<ProductSize>>> {

    @Override
    protected ProductSize mapFieldsToItem(List<String> fields) {
        return new ProductSize(
                Integer.valueOf(fields.get(0)),
                Integer.valueOf(fields.get(1)),
                Boolean.valueOf(fields.get(2)),
                Boolean.valueOf(fields.get(3)));
    }

    @Override
    protected Collector<ProductSize, ?, Map<Integer, List<ProductSize>>> getCollector() {
        return Collectors.groupingBy(ProductSize::getProductId);
    }
}