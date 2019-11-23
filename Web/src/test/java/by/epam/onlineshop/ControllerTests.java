package by.epam.onlineshop;

import org.hibernate.Filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epam.onlineshop.config.*;
import by.epam.onlineshop.service.ProductService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestPropertySource(locations = "classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebConfig.class,
        SecurityConfig.class,
        DataConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class ControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {


        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Autowired
    private ProductService productService;

    @Test
    public void configTest() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("homeController"));
        Assert.assertNotNull(wac.getBean("productController"));
        Assert.assertNotNull(wac.getBean("shoppingCartController"));
        Assert.assertNotNull(wac.getBean("userController"));
    }

    @Test
    public void userControllerStatus() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/login")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void productControllerStatus2() throws Exception {
        this.mockMvc.perform(get("/admin/product")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("templates/product")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shoppingCartControllerStatus() throws Exception {
        this.mockMvc.perform(get("/shoppingCart")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("templates/shoppingCart")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void homeControllerStatus() throws Exception {
        this.mockMvc.perform(get("/home")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("templates/home")).andDo(MockMvcResultHandlers.print());
    }
}
