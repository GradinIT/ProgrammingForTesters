package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;


public class TestThatDepartmentIsStoredInDatabase {
    DepartmentDao departmentDao = mock(DepartmentDao.class);

    private final Integer departmentId = 3;
    private final String departmentName = "test";
    @Before
    public void setUp(){
        when(departmentDao.save(any(DepartmentDatabaseEntry.class))).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentName(departmentName)
                .departmentId(departmentId)
                .build());

    }

    @Test
    public void TestSaveIsStoredInDataBase(){

        DepartmentDatabaseEntry databaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        DepartmentDatabaseEntry databaseEntrySave = departmentDao.save(databaseEntry);

        Assert.assertEquals(databaseEntry.getDepartmentId(),databaseEntrySave.getDepartmentId());
    }
}
