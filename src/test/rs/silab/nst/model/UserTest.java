package rs.silab.nst.model;
import org.junit.Before;
import org.junit.Test;
import rs.silab.nst.model.User;
import java.util.LinkedList;
import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User(1, "irena@fon.com", "irena", "milinkovic",
                "irena", "irena", "irena",
                null, new LinkedList<Role>(), null);
    }

    @Test
    public void toString_returnsCorrectStringRepresentation() {
        String expectedString = "User{id=1, email='irena@fon.com', firstname='irena'," +
                " lastname='milinkovic', password='irena', username='irena', " +
                "companyBean=null, roles=[], rolesS=null}";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void constructorShouldSetValues() {
        assertEquals("irena@fon.com", user.getEmail());
        assertEquals("irena", user.getFirstname());
        assertEquals("milinkovic", user.getLastname());
        assertEquals("irena", user.getPassword());
    }

    @Test
    public void checkEqualsMethod() {
        User user_2 = new User(1, "irena@fon.com", "irena",
                "milinkovic", "irena", "irena",
                "irena", new Company(), null, null);
        User user_3 = new User(2, "irena@fon.com", "irena",
                "milinkovic", "irena", "irena",
                "irena", new Company(), null, null);
        assertTrue(user.equals(user_2));
        assertFalse(user.equals(user_3));
    }

    @Test
    public void checkAddNewRole() {
        //Arrange
        Role role = new Role(3, "admin_role", "this is admin role");
       //Assert
        assertEquals(0, user.getRoles().size());
        //Act
        user.addRole(role);
        //Assert
        assertEquals(1, user.getRoles().size());

    }

}