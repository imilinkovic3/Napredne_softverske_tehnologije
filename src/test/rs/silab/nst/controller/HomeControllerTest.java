package rs.silab.nst.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.User;
import rs.silab.nst.service.UserService;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by milinkoi on 03.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView() throws Exception {
        User first =  new User(1, "irena@fon.com", "irena", "milinkovic", "irena", "irena", "irena", new Company(), null, null);
        User second =  new User(1, "irena@fon.com", "irena", "milinkovic", "irena", "irena", "irena", new Company(), null, null);


        when(userService.findAllUsers()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("usercreation"))
                .andExpect(forwardedUrl("/WEB-INF/views/users/usercreation.jsp"));

        verify(userService, times(1)).findAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Configuration
    static class LoginControllerTestConfiguration {

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }


    }
}
