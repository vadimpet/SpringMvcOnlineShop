package by.epam.onlineshop.service;

import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.repository.CategoryRepository;
import by.epam.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByProducts(Product product) {
        return categoryRepository.findByProducts(product);
    }

    public Category findById(String id) {
        Optional<Category> category = categoryRepository.findById(Long.valueOf(id));
        return category.get();
    }

    public List<Product> findByCategory(Category category) {
        List<Product> list = productRepository.findAllByCategories(category);
        System.out.println("FOUND TRASH" + list);
        return list;
    }

}
