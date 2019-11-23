package by.epam.onlineshop.service;

import by.epam.onlineshop.exception.NotEnoughProductsInStockException;
import by.epam.onlineshop.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    void clearProducts();

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}