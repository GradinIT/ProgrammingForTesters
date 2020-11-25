package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Department;
import se.jensen.entity.Employee;
import se.jensen.exercise.DepartmentTestBuilder;
import se.jensen.exercise.EmployeeTestBuilder;
import se.jensen.entity.Department;
import javax.ws.rs.core.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

public class TestThatDepartmentIsStoredInDatabase {
    @Autowired
    private DepartmentDao departmentDao;

    Integer DEPARTMENTID = 1;
    String DEPARTMENTNAME = "Departmentname";
    DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
            .departmentId(DEPARTMENTID)
            .departmentName(DEPARTMENTNAME)
            .build();

    @Test
    public void testIsStored() {
        System.out.println("start test");
        DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntrySaved.getDepartmentId());
        System.out.println("Test finish");
    }

}
