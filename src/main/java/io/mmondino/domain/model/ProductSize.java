package io.mmondino.domain.model;

public class ProductSize {

    private Integer id;
    private Integer productId;
    private Boolean backSoon;
    private Boolean special;

    public ProductSize(Integer id, Integer productId, Boolean backSoon, Boolean special) {
        this.id = id;
        this.productId = productId;
        this.backSoon = backSoon;
        this.special = special;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Boolean getBackSoon() {
        return backSoon;
    }

    public Boolean getSpecial() {
        return special;
    }
}
