package org.anotes.example.twitterflight.domain.entity;

import java.math.BigDecimal;

/**
 * User: gmc
 * Date: 27/05/2014
 */
public class Product {
    private Long gkey;
    private String name;
    private String brand;
    private BigDecimal price;
    private String imageUrl;

    public Product() {
    }

    public Product(Long gkey, String name, String brand, BigDecimal price, String imageUrl) {
        this.gkey = gkey;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getGkey() {
        return gkey;
    }

    public void setGkey(Long gkey) {
        this.gkey = gkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (gkey != null ? !gkey.equals(product.gkey) : product.gkey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return gkey != null ? gkey.hashCode() : 0;
    }

    public static Product forSearching(Long productGkey) {
        Product product = new Product();
        product.setGkey(productGkey);
        return product;
    }
}
