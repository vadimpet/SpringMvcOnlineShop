package by.epam.onlineshop.validator;

import by.epam.onlineshop.form.ProductForm;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProductFormValidator implements Validator {

    @Autowired
    private ProductService productService;

    // This validator only checks for the ProductForm.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;

        // Check the fields of ProductForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty.productForm.id");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.productForm.quantity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.productForm.description");

        Long id = productForm.getId();

        System.out.println("Validator id:" + id);

        if (id != null && id > 0) {
            if (productForm.isNewProduct()) {

                Boolean present = productService.findById(id).isPresent();

                if (present == true) {
                    errors.rejectValue("id", "Duplicate.productForm.id");
                }
            }
        }
    }

}

