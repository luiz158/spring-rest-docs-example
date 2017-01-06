package com.springrestdocs;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static com.sun.scenario.Settings.get;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureRestDocs("/v1")
//@AutoConfigureMockMvc
public class V1ApplicationTests {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets/v1");

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
		user.setFullnamne("Test Username");
		user.setUsername("usernameTest");

		this.mockMvc.perform(post("/api/v1/users")
									 .accept(MediaType.APPLICATION_JSON)
									 .content(objectMapper.writeValueAsString(user))
									 .contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}

}
