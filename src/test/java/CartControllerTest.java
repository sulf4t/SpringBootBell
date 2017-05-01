import com.bellproject.Main;
import com.bellproject.controler.CartController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * based tests for {@link CartController}.
 *
 * @author edouard .
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Main.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnUser1LineItem() throws Exception {

        this.mockMvc.perform(get("/cart/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"productId\":1,\"quantity\":1}]"));
    }

    @Test
    public void shouldReturnUser2LineItem() throws Exception {

        this.mockMvc.perform(get("/cart/2").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldReturnUserIdNotFound() throws Exception {

        this.mockMvc.perform(get("/cart/5").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteProductToUser1() throws Exception {

        this.mockMvc.perform(get("/cart/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"productId\":1,\"quantity\":2}]"));

        this.mockMvc.perform(delete("/cart/1/1"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/cart/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"productId\":1,\"quantity\":1}]"));
    }

    @Test
    public void shouldAddProductToUser1() throws Exception {

        this.mockMvc.perform(get("/cart/2").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        this.mockMvc.perform(put("/cart/2/2"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/cart/2").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"productId\":2,\"quantity\":1}]"));
    }
}
