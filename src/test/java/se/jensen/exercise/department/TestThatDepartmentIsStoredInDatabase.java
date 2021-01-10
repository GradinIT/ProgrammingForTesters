package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.test.category.UnitTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestThatDepartmentIsStoredInDatabase implements UnitTest {

    DepartmentDao departmentDao = mock(DepartmentDao.class);
    private final Integer DEPARTMENT_ID=2;
    private final String DEPARTMENT_NAME="Junit";

    @Before
    public void setupMock(){
        when(departmentDao.save(any(DepartmentDatabaseEntry.class)))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENT_ID)
                        .departmentName(DEPARTMENT_NAME)
                        .build());
    }
    @Test
    public void testIsStored(){
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();

        DepartmentDatabaseEntry departmentDatabaseEntry1 = departmentDao.save(departmentDatabaseEntry);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntry1.getDepartmentId());
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentName(),departmentDatabaseEntry1.getDepartmentName());

    }



}
