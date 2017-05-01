import com.bellproject.Main;
import com.bellproject.controler.CartController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * based tests for {@link com.bellproject.controler.UserController}.
 *
 * @author edouard .
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Main.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnUserList() throws Exception {

        this.mockMvc.perform(get("/user").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Francis\"},{\"id\":2,\"name\":\"Edouard\"},{\"id\":3,\"name\":\"Divya\"}]"));
    }

    @Test
    public void shouldReturnUser1() throws Exception {

        this.mockMvc.perform(get("/user/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Francis\"}"));
    }

    @Test
    public void shouldReturnUserIdNotFound() throws Exception {

        this.mockMvc.perform(get("/user/5").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isNotFound());
    }

}
