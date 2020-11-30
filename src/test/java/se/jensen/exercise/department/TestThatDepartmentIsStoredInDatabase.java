package se.jensen.exercise.department;
import se.jensen.dao.*;

import liquibase.pro.packaged.I;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

public class TestThatDepartmentIsStoredInDatabase {

    DepartmentDao departmentDao = mock(DepartmentDao.class);

    // Mock the DB
    private final Integer DEPARTMENTID = 1;
    private final String  DEPARTMENTNAME = "newDepartment";

    @Before
    public void setUpMock()
    {
        when(departmentDao.save(any(DepartmentDatabaseEntry.class)))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());
    }

    @Test
    public void testIsStored()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);

        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntrySaved.getDepartmentId());

        Assert.assertEquals(departmentDatabaseEntry.getDepartmentName(),departmentDatabaseEntrySaved.getDepartmentName());
        verify(departmentDao, times(1)).save(any());
        verify(departmentDao, atLeastOnce()).save(any());
    }
}
