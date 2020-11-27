package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.DepartmentDao;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestThatDepartmentIsStoredInDatabase {
    DepartmentDao departmentDao= mock(DepartmentDao.class);

    private final Integer DEPARTMENTID = 1;
    private final String DEPARTMENTNAME = "DepartmentName";
    @Before
    public void setUpMock() {
        when(departmentDao.save(any(DepartmentDatabaseEntry.class)))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());
    }
    @Test
    public void testIsStored(){
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();


        DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntrySaved.getDepartmentId());

        verify(departmentDao,times(1)).save(departmentDatabaseEntry);
    }
}
