package se.jensen.exercise.department;
import org.junit.Test;
import se.jensen.entity.Department;



public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testIdNull(){

        Department.builder()
                .departmentId(null)
                .departmentName("")
                .build();

    }

    @Test(expected = NullPointerException.class)
    public void testNameNull(){
        Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();

    }


}
