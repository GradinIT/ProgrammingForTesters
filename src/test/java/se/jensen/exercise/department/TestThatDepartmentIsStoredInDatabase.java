package se.jensen.exercise.department;

import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;

public class TestThatDepartmentIsStoredInDatabase {

    Integer DEPARTMENTID = 1;
    String DEPARTMENTNAME = "Departmentname";
    @Before
    public void setUp() {
    }

    @Test
    public void testIsStored(){
        DepartmentDatabaseEntry departmentDatabaseEntry=DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
    }
}
