package by.epam.onlineshop.repository;

import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(long id);

    //List<Product> findByCategory(Category category);

    List<Category> findAll();

    List<Category> findByProducts(Product product);

}
