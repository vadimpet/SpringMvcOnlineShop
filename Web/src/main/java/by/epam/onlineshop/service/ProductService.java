package by.epam.onlineshop.service;

import by.epam.onlineshop.form.ProductForm;
import by.epam.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    void save(ProductForm productForm);
    void delete(long id);
    void edit(long id, Product newProduct);

    List<Product> findAllByOrderByIdAsc();

    Page<Product> findAllProductsPageable(Pageable pageable);
    long count();

}
