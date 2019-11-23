package by.epam.onlineshop.service;

import by.epam.onlineshop.exception.ProductException;
import by.epam.onlineshop.form.ProductForm;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id){

        return productRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(findById(id).get());
    }


    public void save(ProductForm productForm) {

        Long id = productForm.getId();

        Product product = null;

        boolean isNew = false;
        if (id != null) {
            Boolean present = findById(id).isPresent();
            if(present == true) {
                product = findById(id).get();
            }
        }
        if (product == null) {
            product = new Product();
        }

        product.setId(id);
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setDescription(productForm.getDescription());

        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }

        productRepository.save(product);

//        Product found = productRepository.getOne(id);
//        found.setName(newProduct.getName());
//        found.setQuantity(newProduct.getQuantity());
//        found.setImage(newProduct.getImage());
//        found.setDescription(newProduct.getDescription());
//        found.setPrice(newProduct.getPrice());

    }

    @Override
    public void edit(long id, Product newProduct) {

        Product found = productRepository.getOne(id);
        found.setName(newProduct.getName());
        found.setQuantity(newProduct.getQuantity());
        found.setImage(newProduct.getImage());
        found.setDescription(newProduct.getDescription());
        found.setPrice(newProduct.getPrice());

    }

    @Override
    public List<Product> findAllByOrderByIdAsc() {
        return null;
    }

    @Override
    public long count() {
        return productRepository.count();
    }
}
