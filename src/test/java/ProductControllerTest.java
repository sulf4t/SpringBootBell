import com.bellproject.Main;
import com.bellproject.controler.ProductController;
import com.bellproject.entity.Product;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * based tests for {@link ProductController}.
 *
 * @author edouard .
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Main.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;



    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void shouldReturnProductList() throws Exception {

        this.mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[{\"id\":1,\"name\":\"MacBook Pro\",\"description\":\"Line of a Macintosh portable computers\",\"price\":2400.0}," +
                                "{\"id\":2,\"name\":\"Sony Vaio\",\"description\":\"Line of a Sony portable computers\",\"price\":2000.0}," +
                                "{\"id\":3,\"name\":\"HP Envy\",\"description\":\"Line of a HP portable computers\",\"price\":1500.0}]"));
    }

    @Test
    public void shouldReturnProduct1Information() throws Exception {

        this.mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"id\":1,\"name\":\"MacBook Pro\",\"description\":\"Line of a Macintosh portable computers\",\"price\":2400.0}"));
    }

    @Test
    public void shouldReturnProductNotFoundForGetRequest() throws Exception {

        this.mockMvc.perform(get("/products/6").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddedProduct() throws Exception {

        String productJson = json(new Product(
                4, "ZenBook", "Line of a Asus portable computers", 1250.0));
        this.mockMvc.perform(post("/products")
                .contentType(getContentType())
                .content(productJson))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[{\"id\":1,\"name\":\"MacBook Pro\",\"description\":\"Line of a Macintosh portable computers\",\"price\":2400.0}," +
                                "{\"id\":2,\"name\":\"Sony Vaio\",\"description\":\"Line of a Sony portable computers\",\"price\":2000.0}," +
                                "{\"id\":3,\"name\":\"HP Envy\",\"description\":\"Line of a HP portable computers\",\"price\":1500.0}," +
                                "{\"id\":4,\"name\":\"ZenBook\",\"description\":\"Line of a Asus portable computers\",\"price\":1250.0}]"));

    }

    private MediaType getContentType()
    {
        return new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
