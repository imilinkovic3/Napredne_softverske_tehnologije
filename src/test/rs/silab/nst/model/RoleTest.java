package rs.silab.nst.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by milinkoi on 29.04.2017.
 */
public class RoleTest {
    Role role;

    @Before
    public void setUp() {

    }

    @Test
    public void toString_returnsCorrectStringRepresentation() {
        role = new Role();
        role.setId(1);
        role.setName("admin_role");
        role.setTitle("this is admin role");

        assertEquals("admin_role", role.toString());
    }



    @Test
    public void constructorShouldSetValues() {
        role = new Role(1,"admin_role", "this is admin role");
        assertEquals(1, role.getId());
        assertEquals("admin_role", role.getName());
        assertEquals("this is admin role", role.getTitle());

    }

    @Test
    public void checkEqualsMethod() {
        role = new Role(1,"admin_role", "this is admin role");
        Role role_2 = new Role(1,"admin_role", "this is admin role");
        Role role_3 = new Role(3,"business_role", "this is business role");

        assertTrue(role.equals(role_2));
        assertFalse(role.equals(role_3));
    }
}
