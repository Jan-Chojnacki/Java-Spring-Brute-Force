package dev.chojnacki.bruteforce;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BruteForceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void loginSuccessful() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "admin")
                        .param("password", "admin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"))
                .andDo(print());
    }

    @Test
    @DirtiesContext
    void incorrectCredentials() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "admin")
                        .param("password", "admin1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DirtiesContext
    void lock() throws Exception {
        for (int i = 0; i < 6; i++) {
            mockMvc.perform(post("/login")
                            .param("username", "a")
                            .param("password", "a")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    )
                    .andDo(print());
        }

        mockMvc.perform(post("/login")
                        .param("username", "admin")
                        .param("password", "admin")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
