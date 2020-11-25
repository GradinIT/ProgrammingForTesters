package se.jensen.exercise.department;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestThatDepartmentIsStoredInDatabase {

        DepartmentDao departmentDao = mock(DepartmentDao.class);
        private final Integer DEPARTMENTID =10;
        private final String DEPARTMENTNAME= "Sales";

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
   DepartmentDatabaseEntry  departmentDatabaseEntry = new DepartmentDatabaseEntry().builder()
            .departmentId(DEPARTMENTID)
            .departmentName(DEPARTMENTNAME)
            .build();
    DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);
    Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntrySaved.getDepartmentId());

    }
}