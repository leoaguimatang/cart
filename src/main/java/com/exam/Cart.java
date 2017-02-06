package com.exam;

import com.exam.cart.Item;
import com.exam.cart.Product;
import com.exam.cart.RuleEngine;
import com.exam.cart.ShoppingCart;
import com.exam.cart.impl.ShoppingCartImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Cart
{
    private static Map<String, Product> products = new HashMap<String, Product>();

    public static void main( String[] args )
    {
        try {
            // load up the products
            Product small = new Product("ult_small", "Unlimited 1GB", 24.90);
            products.put(small.getCode(), small);

            Product medium = new Product("ult_medium", "Unlimited 2GB", 29.90);
            products.put(medium.getCode(), medium);

            Product large = new Product("ult_large", "Unlimited 5GB", 44.90);
            products.put(large.getCode(), large);

            Product pack = new Product("1gb", "1 GB Data-pack", 9.90);
            products.put(pack.getCode(), pack);

            ShoppingCart cart = new ShoppingCartImpl("rules");
            System.out.println("\nScenario #1");
            cart.add(new Item(small, 3));
            cart.add(new Item(large, 1));
            RuleEngine.process(cart);

            System.out.println("\nScenario #2");
            cart = new ShoppingCartImpl("rules");
            cart.add(new Item(small, 2));
            cart.add(new Item(large, 4));
            RuleEngine.process(cart);

            System.out.println("\nScenario #3");
            cart = new ShoppingCartImpl("rules");
            cart.add(new Item(small, 1));
            cart.add(new Item(medium, 2));
            RuleEngine.process(cart);

            System.out.println("\nScenario #4");
            cart = new ShoppingCartImpl("rules");
            cart.add(new Item(small, 1), "I<3AMAYSIM");
            cart.add(new Item(pack, 1));
            RuleEngine.process(cart);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
