package Database;

import com.Application;
import com.Database.ProductDatabase;
import com.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDatabaseTest {

    @Test
    public void getProductsByLocation_ShouldReturnProductsFromTable() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);

        String productLocation = "C3";
        List<Product> actualProducts = database.getProductsByLocation(productLocation);

        Product actualProduct = actualProducts.get(0);
        assertEquals("Twix", actualProduct.getName());
    }
}
