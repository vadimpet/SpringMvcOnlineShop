package by.epam.onlineshop;

import by.epam.onlineshop.config.DataConfig;
import by.epam.onlineshop.config.SecurityConfig;
import by.epam.onlineshop.config.WebConfig;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.service.ProductService;
import by.epam.onlineshop.service.ShoppingCartService;
import by.epam.onlineshop.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@ContextConfiguration(classes = {
        WebConfig.class,
        SecurityConfig.class,
        DataConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    UserService userService;

    @Test
    public void testProductService() {

        Assert.assertEquals("Java", productService.findById(1L).get().getName());
    }

    @Test
    public void testCartService() {

        Product product = new Product();
        product.setId(12L);
        product.setName("Test");
        product.setQuantity(1);
        product.setDescription("Description");
        product.setPrice(12.99);

        cartService.addProduct(product);

        Assert.assertEquals(new BigDecimal(12.99).setScale(2, BigDecimal.ROUND_HALF_EVEN), cartService.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }

    @Test
    public void testUserService() {

        Assert.assertEquals("Vadim", userService.findUserByEmail("v.v.petrusenko@mail.ru").getFirstname());
    }

}
