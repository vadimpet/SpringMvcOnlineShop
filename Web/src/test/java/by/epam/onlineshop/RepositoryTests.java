package by.epam.onlineshop;

import by.epam.onlineshop.config.DataConfig;
import by.epam.onlineshop.config.SecurityConfig;
import by.epam.onlineshop.config.WebConfig;
import by.epam.onlineshop.model.Product;
import by.epam.onlineshop.model.Role;
import by.epam.onlineshop.repository.ProductRepository;
import by.epam.onlineshop.repository.RoleRepository;
import by.epam.onlineshop.repository.UserRepository;
import by.epam.onlineshop.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@ContextConfiguration(classes = {
        WebConfig.class,
        SecurityConfig.class,
        DataConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RepositoryTests {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testProductFindById() {

        Long pr = productRepository.findById(1L).get().getId();
        Long id = 1L;

        Assert.assertEquals(pr, id);
    }

    @Test
    public void testFindByRole() {

        Assert.assertEquals("ROLE_ADMIN", roleRepository.findByRole("ROLE_ADMIN").getRole());
    }

    @Test
    public void testUserFindByEmail() {

        Assert.assertEquals("Vadim", userRepository.findByEmail("v.v.petrusenko@mail.ru").getFirstname());
    }
}
