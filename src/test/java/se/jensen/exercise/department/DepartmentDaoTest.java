package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

import javax.ws.rs.core.Application;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

public class DepartmentDaoTest implements UnitTest {
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testSaveNewDepartment(){
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper
                .map(DepartmentTestBuilder.build())));
        Assert.assertNotNull(department);
    }

    @Test
    public void testFindAllDepartments(){
        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertNotNull(departments);
        Assert.assertEquals(3, departments.size());
    }
    @Test
    public void testFindDepartmentById(){
        Integer department_Id = Integer.valueOf(1);
       Department department  = DepartmentDatabaseEntryMapper.map(departmentDao.findById(department_Id).get());
       Assert.assertNotNull(department);
       Assert.assertEquals(department_Id,department.getDepartmentId());
    }
    @Test
    public void testDeleteDepartment(){
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper
                .map(Department.builder()
                        .departmentId(4)
                        .departmentName("toDelete")
                        .build())));
        Assert.assertNotNull(department);
       List<Department> departmentList = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
       Assert.assertEquals(4,departmentList.size());
        departmentDao.delete(DepartmentDatabaseEntryMapper.map(department));
        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertEquals(3,departments.size());

    }



}
