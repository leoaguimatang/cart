package com.exam.cart;

import com.exam.cart.impl.ShoppingCartImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Map<String, Product> products = new HashMap<String, Product>();

    @Before
    public void setUp() throws Exception {
        Product product = new Product("ult_small", "Unlimited 1GB", 24.90);
        products.put(product.getCode(), product);

        product = new Product("ult_medium", "Unlimited 2GB", 29.90);
        products.put(product.getCode(), product);

        product = new Product("ult_large", "Unlimited 5GB", 44.90);
        products.put(product.getCode(), product);

        product = new Product("1gb", "1 GB Data-pack", 9.90);
        products.put(product.getCode(), product);

        cart = new ShoppingCartImpl();
    }

    @Test
    public void testAddItem() throws Exception {
        Item item = new Item(products.get("ult_small"), 2);
        cart.add(item);

        Assert.assertEquals(cart.items().size(), 1);
        Assert.assertEquals(cart.total(), 49.80, 0.009);
    }

    @Test
    public void testTotal() throws Exception {
        Item item = new Item(products.get("ult_small"), 2);
        cart.add(item);

        item = new Item(products.get("ult_medium"), 1);
        cart.add(item);

        item = new Item(products.get("ult_large"), 1);
        cart.add(item);

        Assert.assertEquals(cart.total(), 124.60, 0.009);
    }

    @Test
    public void testItems() throws Exception {
        Item item = new Item(products.get("ult_small"), 2);
        cart.add(item);

        item = new Item(products.get("ult_medium"), 1);
        cart.add(item);

        item = new Item(products.get("ult_large"), 1);
        cart.add(item);

        Assert.assertEquals(cart.items().size(), 3);
    }
}