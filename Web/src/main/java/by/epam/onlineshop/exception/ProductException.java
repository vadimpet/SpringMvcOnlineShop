package by.epam.onlineshop.exception;

import by.epam.onlineshop.model.Product;

public class ProductException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "No product found with current params";

    public ProductException(Product product) {
        super(DEFAULT_MESSAGE);
    }

    public ProductException(String message){
        super(message);
    }
}
