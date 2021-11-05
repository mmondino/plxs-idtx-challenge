package io.mmondino.infrastructure.data.loader;

import io.mmondino.domain.model.ProductSizeStock;
import io.mmondino.infrastructure.data.loader.base.CsvToMapDataLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductSizeStockCsvToMapDataLoader extends CsvToMapDataLoader<ProductSizeStock, Integer, Integer, Map<Integer, Integer>> {

    @Override
    protected ProductSizeStock mapFieldsToItem(List<String> fields) {
        return new ProductSizeStock(Integer.valueOf(fields.get(0)), Integer.valueOf(fields.get(1)));
    }

    @Override
    protected Collector<ProductSizeStock, ?, Map<Integer, Integer>> getCollector() {
        return Collectors.toMap(ProductSizeStock::getProductSizeId, ProductSizeStock::getQuantity);
    }
}