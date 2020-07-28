package repository;

import com.Application;
import com.repository.ProductDatabase;
import com.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDatabaseTest {

    @Test
    public void getProductsByLocation_ShouldReturnProductsFromTable() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        ProductDatabase database = new ProductDatabase(jdbcTemplate);

        String productLocation = "C3";
        List<Product> actualProducts = database.getProductsByLocation(productLocation);

        Product actualProduct = actualProducts.get(0);
        assertEquals("Twix", actualProduct.getName());
    }
}
