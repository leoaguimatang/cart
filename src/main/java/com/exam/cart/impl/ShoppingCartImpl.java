package com.exam.cart.impl;

import com.exam.cart.Item;
import com.exam.cart.Product;
import com.exam.cart.ShoppingCart;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartImpl implements ShoppingCart {
    private List<Item> items = new ArrayList<Item>();
    private String promo = "";
    private double discount;
    private static String pricingRule;

    public ShoppingCartImpl() {
    }

    public ShoppingCartImpl(String rule) {
        setRule(rule);
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Adds an item to the cart.
     * @param item
     */
    @Override
    public void add(Item item) {
        if (items.contains(item)) {
            items.stream().filter(itm -> itm.equals(item))
                .forEach(itm -> {
                    if ( itm.equals(item)) {
                        itm.setQuantity(itm.getQuantity() + item.getQuantity());
                    };
                });
        }
        items.add(item);
    }

    /**
     * Adds an item to the cart accompanied by a promo code.
     * @param item
     * @param promo
     */
    @Override
    public void add(Item item, String promo) {
        add(item);
        this.promo = promo;
    }

    /**
     * Computes the total amount of the cart contents.
     * @return total
     */
    @Override
    public double total() {
        double total = 0;
        for (Item item : items) {
            Product product = item.getProduct();
            if (!product.isBundled()) {
                total += item.getQuantity() * product.getPrice();
            }
        }

        total -= discount;
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Return the items in the cart.
     * @return items
     */
    @Override
    public List<Item> items() {
        return items;
    }

    public double addDiscount(double discount) {
        return this.discount += discount;
    }

    public void setRule(String rule) {
        pricingRule = "rules/" + rule.concat(".drl");
    }

    @Override
    public String getRule() {
        return pricingRule;
    }

    /**
     * Retrieve resource content
     */
    protected InputStream getResource(String resource) {
        return getClass().getClassLoader().getResourceAsStream(resource);
    }
}
