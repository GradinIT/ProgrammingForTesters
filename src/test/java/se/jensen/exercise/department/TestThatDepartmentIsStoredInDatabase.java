package se.jensen.exercise.department;
import liquibase.pro.packaged.I;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestThatDepartmentIsStoredInDatabase {
    DepartmentDao departmentDao = mock(DepartmentDao.class);

    private final Integer departmentId = 2;
    private final String departmentName = "Ekonomi";

    @Before
    public void setUpMock() {
        when(departmentDao.save(any(DepartmentDatabaseEntry.class)))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(departmentId)
                        .departmentName(departmentName)
                        .build());
    }

    @Test
    public void testIsStored() {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(), departmentDatabaseEntrySaved.getDepartmentId());
    }
}