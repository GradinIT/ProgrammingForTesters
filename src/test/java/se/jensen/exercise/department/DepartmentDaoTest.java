package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

import javax.ws.rs.core.Application;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@Category(UnitTest.class)

public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void a_testGetDepartmentById()
    {
        Optional <DepartmentDatabaseEntry> optionalDepartment = departmentDao.findById(1);

        Assert.assertTrue(optionalDepartment.isPresent());
    }
//-------------------------------------------------------------------------------------------------------------

    @Test
    public void b_testGetAllDepartments()
    {
        List <DepartmentDatabaseEntry> allDepartments = departmentDao.findAll();
        
        Assert.assertNotNull(allDepartments);
        Assert.assertEquals(3, allDepartments.size());
    }

//-------------------------------------------------------------------------------------------------------------
    @Test
    public void c_testSaveNewDepartment()
    {
        DepartmentDatabaseEntry departmentSaved = departmentDao.save(DepartmentDatabaseEntry.builder()
                .departmentId(10)
                .departmentName("Testers")
                .build());

        List<DepartmentDatabaseEntry> departmentsAfterSave = departmentDao.findAll();

        Assert.assertNotNull(departmentSaved);
        Assert.assertTrue(departmentDao.findById(10).isPresent());
        Assert.assertEquals("Testers", departmentDao.findById(10).get().getDepartmentName());
        Assert.assertEquals(4, departmentsAfterSave.size() );
    }
//-------------------------------------------------------------------------------------------------------------

    @Test
    public void d_testUpdateDepartment()
    {
        DepartmentDatabaseEntry departmentUpdated = departmentDao.save(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(10)
                .departmentName("Programmers")
                .build()));

        List <DepartmentDatabaseEntry> departmentsAfterUpdate = departmentDao.findAll();

        Assert.assertNotNull(departmentUpdated);
        Assert.assertTrue(departmentDao.findById(10).isPresent());
        Assert.assertEquals("Programmers", departmentDao.findById(10).get().getDepartmentName());
        Assert.assertEquals(4, departmentsAfterUpdate.size());
    }
//-------------------------------------------------------------------------------------------------------------

    @Test
    public void e_testDeleteNewDepartment()
    {
        departmentDao.delete(DepartmentDatabaseEntry.builder()
                .departmentId(10)
                .departmentName("Programmers")
                .build());

        List <DepartmentDatabaseEntry> departmentsAfterDelete = departmentDao.findAll();

        Assert.assertEquals(3, departmentsAfterDelete.size());
        Assert.assertFalse(departmentDao.findById(10).isPresent());
    }
}

