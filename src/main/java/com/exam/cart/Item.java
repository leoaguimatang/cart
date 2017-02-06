package com.exam.cart;

import java.io.Serializable;

/**
 * Holds the item details.
 */
public class Item implements Serializable {
    private Product product;
    private int quantity;
    private String promo;

    public Item() {}

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item(Product product, int quantity, String promo) {
        this.product = product;
        this.quantity = quantity;
        this.promo = promo;
    }

    // Getters and Setters

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return product.equals(item.product);

    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}