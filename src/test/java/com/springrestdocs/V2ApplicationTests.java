package com.springrestdocs;

import static org.hamcrest.core.Is.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V2ApplicationTests {

    @Rule
    public final JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("build/generated-snippets/v2");

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                                      .apply(documentationConfiguration(this.restDocumentation))
                                      .alwaysDo(document("{method-name}/"))
                                      .build();
    }

    @Test
    public void createUser() throws Exception {

        User user = new User();
        user.setFullname("Test UsernameV2");
        user.setUsername("usernameTestV2");

        this.mockMvc.perform(post("/api/v2/users")
                                     .accept(MediaType.APPLICATION_JSON)
                                     .content(objectMapper.writeValueAsString(user))
                                     .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setFullname("Test UsernameV2");
        user.setUsername("usernameTestV2");

        this.mockMvc.perform(put("/api/v2/users")
                                     .accept(MediaType.APPLICATION_JSON)
                                     .content(objectMapper.writeValueAsString(user))
                                     .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("username", is(user.getUsername())))
                    .andExpect(jsonPath("fullname", is(user.getFullname())));
    }

}
