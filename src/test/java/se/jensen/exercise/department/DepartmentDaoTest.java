package se.jensen.exercise.department;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;

import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Application;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;


    @Test
    public void a_testSaveNewDepartment()
    {
        List<Department> departmentsBeforeSave = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("\n-------Department before save-------");
        departmentsBeforeSave.stream().forEach(System.out::println);

        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(10)
                .departmentName("Testers")
                .build())));

        List<Department> departmentsAfterSave = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("-------Department after save-------");
        departmentsAfterSave.stream().forEach(System.out::println);

        Assert.assertNotNull(department);
        Assert.assertEquals(4, departmentsAfterSave.size() );
    }

    @Test
    public void b_testUpdateNewDepartment()
    {
        List<Department> departmentsBeforeUpdate = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("\n------- Department before update-------");
        departmentsBeforeUpdate.stream().forEach(System.out::println);

        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(10)
                .departmentName("Programmers")
                .build())));

        List<Department> departmentsAfterUpdate = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("-------Department after update-------");
        departmentsAfterUpdate.stream().forEach(System.out::println);

        Assert.assertNotNull(department);
        Assert.assertEquals(4, departmentsAfterUpdate.size());
    }

    @Test
    public void c_testDeleteNewDepartment()
    {
        List<Department> departmentsBeforeDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("\n-------Department before delete-------");
        departmentsBeforeDelete.stream().forEach(System.out::println);

        departmentDao.delete(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(10)
                .departmentName("Programmers")
                .build()));

        List<Department> departmentsAfterDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("-------Department after delete-------");
        departmentsAfterDelete.stream().forEach(System.out::println);

        Assert.assertEquals(3, departmentsAfterDelete.size());
    }

}

