package se.jensen.exercise.department;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.test.category.UnitTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Category(UnitTest.class)

public class TestThatDepartmentIsStoredInDatabase {

    private final Integer DEPARTMENT_ID = 15;
    private final String DEPARTMENT_NAME = "name";

    DepartmentDao departmentDao = mock(DepartmentDao.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENT_ID)
                        .departmentName(DEPARTMENT_NAME)
                        .build());
    }

    @Test
    public void testToStore() {
        departmentDao.save(DepartmentDatabaseEntry.builder()
                .departmentName(DEPARTMENT_NAME)
                .departmentId(DEPARTMENT_ID)
                .build());

        verify(departmentDao,times(1)).save(any());
    }
}
