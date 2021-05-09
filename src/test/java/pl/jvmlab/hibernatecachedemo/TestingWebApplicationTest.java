package pl.jvmlab.hibernatecachedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private String userId = UUID.randomUUID().toString();

    @Test
    public void shouldEditPost() throws Exception {

        final String postId = this.mockMvc.perform(
                post("/posts")
                        .header("userId", userId)
                .content("{\"text\": \"Sample post\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        this.mockMvc.perform(
                put("/posts/" + postId)
                        .header("userId", userId)
                        .content("{\"text\": \"Edited post\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetAllPosts() throws Exception {

        this.mockMvc.perform(
                post("/posts")
                        .header("userId", userId)
                        .content("{\"text\": \"Sample post\"}")
                        .contentType(MediaType.APPLICATION_JSON));

        for (int i = 0; i < 4; i++) {
            this.mockMvc.perform(
                    get("/posts"))
                    .andExpect(status().isOk());
        }

    }
}