package pl.angler.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import pl.angler.AnglerApplication;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = AnglerApplication.class)
public class FishingTripControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    private static final String CLIENT_ID = "fishlogclientid";
    private static final String CLIENT_SECRET = "ZmlzaGxvZy1iYWNrZW5kLWFwcGxpY2F0aW9uDQo";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String EMAIL = "jan.szewczyk1997@gmail.com";
    private static final String PASS = "zaq1@WSXcde3";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    private String getAccessToken(String email, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", email);
        params.add("password", password);

        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
        final String accessToken = getAccessToken("admin", "nimda");

        String employeeString = "{\"email\":\"" + EMAIL + "\",\"name\":\"" + EMAIL + "\",\"age\":30}";

        // @formatter:off

        mockMvc.perform(post("/employee")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(CONTENT_TYPE)
                .content(employeeString)
                .accept(CONTENT_TYPE))
                .andExpect(status().isCreated());

//        mockMvc.perform(get("/trip")
//                .header("Authorization", "Bearer " + accessToken)
//                .
//                .accept(CONTENT_TYPE))
//                .andExpect(status().isOk()
////                .andExpect(content().contentType(CONTENT_TYPE))
////                .andExpect(jsonPath("$.name", is(EMAIL))
//                );

        // @formatter:on
    }
}