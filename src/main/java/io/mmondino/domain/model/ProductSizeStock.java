package io.mmondino.domain.model;

public class ProductSizeStock {

    private Integer productSizeId;
    private Integer quantity;

    public ProductSizeStock(Integer productSizeId, Integer quantity) {
        this.productSizeId = productSizeId;
        this.quantity = quantity;
    }

    public Integer getProductSizeId() {
        return productSizeId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
