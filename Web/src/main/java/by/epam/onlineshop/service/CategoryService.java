package by.epam.onlineshop.service;

import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category findById(String id);

    List<Product> findByCategory(Category category);

    List<Category> findAll();

    List<Category> findByProducts(Product product);
}
