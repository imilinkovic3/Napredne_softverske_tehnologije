package rs.silab.nst.model;

import org.junit.Before;
import org.junit.Test;
import rs.silab.nst.model.Process;

import static org.junit.Assert.*;

public class ProcessTest {

    private Process process;

    @Before
    public void setUP() {
    }


    @Test
    public void toString_returnsCorrectStringRepresentation() {
        Process p = new Process();
        p.setId(1);
        p.setName("Proces name");
        p.setDescription("description");
        String expectedString = "Process{id=1, company=null, description='description', name='Proces name', text='null', nodes=[]}";
        assertEquals(expectedString, p.toString());
    }

    @Test
    public void constructorShouldSetValues() {
        process = new Process(22, new Company(), "This is Process 22", "Process 22", "", new Process());
        assertEquals(22, process.getId());
        assertEquals("This is Process 22", process.getDescription());
        assertEquals("Process 22", process.getName());
    }


    @Test
    public void checkEqualsMethod (){
        process = new Process(22, new Company(), "This is Process 22", "Process 22", "", new Process());
        Process process_2 = new Process(22, new Company(), "This is Process 22", "Process 22", "", new Process());
        Process process_3 = new Process(25, new Company(), "This is Process 22", "Process 22", "", new Process());
        assertTrue(process.equals(process_2));
        assertFalse(process_2.equals(process_3));
    }

}