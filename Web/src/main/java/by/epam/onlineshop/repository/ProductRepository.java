package by.epam.onlineshop.repository;

import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product findByName(String name);
    List<Product> findAllByCategories(Category category);
    List<Product> findAllByOrderByIdAsc();
}
