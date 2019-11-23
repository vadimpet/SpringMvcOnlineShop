package by.epam.onlineshop.controller;

import by.epam.onlineshop.form.ProductForm;
import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.service.CategoryService;
import by.epam.onlineshop.service.ProductService;
import by.epam.onlineshop.validator.ProductFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    private ProductFormValidator productFormValidator;

    private CategoryService categoryService;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }
    }

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ProductFormValidator productFormValidator) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productFormValidator = productFormValidator;
    }

    @RequestMapping(value = "/product/edit/{id}")
    public String editProduct(@PathVariable("id") long productId, @ModelAttribute("productForm") Product productForm, BindingResult bindingResult, Model model){
        productFormValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "edit");
            return "templates/product";
        }
        productService.edit(productId, productForm);
        logger.debug(String.format("Product with id: %s has been successfully edited.", productId));

        return "redirect:/home";
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long productId) {
        Product product = productService.findById(productId).get();

        if (product != null) {
            productService.delete(productId);
            logger.debug(String.format("Product with id: %s successfully deleted.", product.getId()));
            return "redirect:/home";
        } else {
            return "templates/errors/404";
        }
    }

    // GET: Show product.
    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "productId", defaultValue = "") Long productId) {
        ProductForm productForm = null;

        Product product;

        if (productId != null && productId > 0) {
            product = productService.findById(productId).get();
            if (product != null) {
                productForm = new ProductForm(product);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
            productForm.setNewProduct(true);

        }
        model.addAttribute("productForm", productForm);
        return "templates/product";
    }

    // POST: Save product
    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
    public String productSave(Model model, //
                              @ModelAttribute("productForm")@Validated ProductForm productForm, //
                              BindingResult result, //
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "templates/product";
        }

        productService.save(productForm);

        return "redirect:/home";
    }

    @RequestMapping("/user/description/{id}")
    public String productDescription(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id).get();
        model.addAttribute("products",product);
        model.addAttribute("categories",categoryService.findByProducts(product));
        return "templates/productView";
    }

    @RequestMapping(value = {"/user/category/{id}"}, method = RequestMethod.GET)
    public String productsByCategory(@PathVariable String id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("products",categoryService.findByCategory(category));
        model.addAttribute("category",category);
        return "templates/category";
    }

}