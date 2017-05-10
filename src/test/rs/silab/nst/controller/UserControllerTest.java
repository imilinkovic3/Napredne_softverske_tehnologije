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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.Role;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by milinkoi on 02.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserControllerTest {


    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private User user;


    @Before
    public void setup() throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(5, "admin", "this is admin role"));
        user = new User(1, "irena@fon.com", "irena", "milinkovic", "irena", "irena", "irena", new Company(), null, null);
        Mockito.when(userService.findByEmailOrUsername(user.getUsername())).thenReturn(user);
    }

    @Test
    public void testDetails() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(get("/details/"+user.getId())).andExpect(status().isOk()).andExpect(redirectedUrl("WEB-INF/views/users/details.jsp"));
    }

//    @Test
//    public void testLogin() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
//        mockMvc.perform(post("/login").param("username", "irena").param("password", "irena").param("firstname", "Irena")
//                .param("lastname", "Milinkovic")).andExpect(status().isOk()).andExpect(redirectedUrl("/nst/home/"));
//    }

    @Test
    public void testUpdateUser() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(get("/update/"+user.getId()).param("id","1")).andExpect(status().isOk()).andExpect(redirectedUrl("/adduser/"));

    }

    @Test
    public void testAdd() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(get("/add")).andExpect(status().isOk()).andExpect(redirectedUrl("/notAdminDetails/"));

    }


    @After
    public void verify() {
        Mockito.verify(this.userService, VerificationModeFactory.times(1)).findByEmailOrUsername("irena@fon.com");
        Mockito.reset();
    }


    @Configuration
    static class LoginControllerTestConfiguration {

        @Bean
        public UserController userController() {
            return Mockito.mock(UserController.class);
        }

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }


    }


}

