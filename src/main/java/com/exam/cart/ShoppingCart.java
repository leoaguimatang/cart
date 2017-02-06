package com.exam.cart;

import java.io.InputStream;
import java.util.List;

/**
 * Holds the shopping cart details.
 */
public interface ShoppingCart {
    String getRule();
    void add(Item item);
    void add(Item item, String promo);
    double total();
    List<Item> items();
}