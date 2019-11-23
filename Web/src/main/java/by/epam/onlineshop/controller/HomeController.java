package by.epam.onlineshop.controller;

import by.epam.onlineshop.model.Category;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.service.CategoryService;
import by.epam.onlineshop.service.ProductService;
import by.epam.onlineshop.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final int INITIAL_PAGE = 0;

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/home")
    public ModelAndView home(@RequestParam("page") Optional<Integer> page) {

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProductsPageable(new PageRequest(evalPage, 6));
        Pager pager = new Pager(products);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories",categoryService.findAll());
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("templates/home");
        return modelAndView;
    }

}
