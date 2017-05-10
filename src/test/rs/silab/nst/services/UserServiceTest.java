package rs.silab.nst.services;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import rs.silab.nst.config.AppConfig;
import rs.silab.nst.model.User;
import rs.silab.nst.model.Company;
import static org.junit.Assert.*;
import rs.silab.nst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.silab.nst.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = new User(1, "irena@fon.com", "irena",
                "milinkovic", "irena", "irena",
                "irena", new Company(), null, null);
        Mockito.when(userService.findById(1)).thenReturn(user);
    }

    @After
    public void tearDown() {
        Mockito.verify(this.userService, VerificationModeFactory.times(1))
                .findByEmailOrUsername("irena@fon.com");
        Mockito.reset();
    }

    @Test
    public void findByIdAndSaveUserTest() throws Exception {
        userService.saveUser(user);
        assertEquals(user,userService.findById(1));
    }
    @Test
    public void findAllUsersTest() throws Exception {
        int size = userService.findAllUsers().size();
        userService.saveUser(user);
        assertEquals((size+1),userService.findAllUsers().size());
    }
    @Test
    public void findByUsernameTest() throws Exception {
        userService.saveUser(user);
        assertEquals(user,userService.findByUsername(user));
    }

    @Test
    public void findByEmailTest() throws Exception {
        userService.saveUser(user);
        assertEquals(user,userService.findByEmail(user));
    }

    @Test
    public void deleteUser() throws Exception {
        userService.saveUser(user);
        int size = userService.findAllUsers().size();
        userService.deleteUser(1);
        assertEquals((size-1),userService.findAllUsers().size());
    }

    @Test
    public void findByEmailOrUsernameTest() throws Exception {
        userService.saveUser(user);
        assertEquals(user,userService.findByEmailOrUsername(user.getUsername()));
        assertEquals(user,userService.findByEmailOrUsername(user.getEmail()));

    }

    @Configuration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
}

