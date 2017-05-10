package rs.silab.nst.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rs.silab.nst.config.AppConfig;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.Role;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by milinkoi on 30.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {AppConfig.class})
@WebAppConfiguration
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserService userService;

    private User user;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(5,"admin","this is admin role"));
        user = new User(1, "irena@fon.com", "irena",
                "milinkovic", "irena", "irena",
                "irena", new Company(), null, null);
        Mockito.when(userService.findByEmailOrUsername(user.getUsername())).thenReturn(user);
        mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
    }

    @Test
    public void testStart() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).
                andExpect(redirectedUrl("WEB-INF/views/login/login.jsp"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/login").param("username", "irena").
                param("password", "irena").param("firstname", "Irena")
                .param("lastname", "Milinkovic")).andExpect(status().isOk()).
                andExpect(redirectedUrl("/nst/home/"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout")).andExpect(status().isOk()).
                andExpect(redirectedUrl("/nst/"));

    }
    @After
    public void verify() {
        Mockito.verify(this.userService, VerificationModeFactory.times(1))
                .findByEmailOrUsername("irena@fon.com");
        Mockito.reset();
    }

    @Configuration
    static class LoginControllerTestConfiguration {
        @Bean
        public LoginController loginController() {
            return Mockito.mock(LoginController.class);
        }

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }


}
