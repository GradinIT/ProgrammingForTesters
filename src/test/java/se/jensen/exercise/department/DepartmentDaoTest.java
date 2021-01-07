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
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;

import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Before
    public void setUp() {
        departmentDao.deleteAll();
        departmentDao.saveAll(Arrays.asList(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Sales")
                .build(),
                DepartmentDatabaseEntry.builder()
                .departmentId(2)
                .departmentName("Management")
                .build()));
    }

    @Test
    public void testToGetAll() {
        List<DepartmentDatabaseEntry> departmentsList = departmentDao.findAll();
        Assert.assertEquals(2, departmentsList.size());
        departmentsList.stream().forEach(System.out::println); //kan ta bort denna, vi har assert och man skriver inte ut i console på detta vis, man kollar i loggar.
    }

    @Test
    public void testToSaveNewDepartment() {
        List<DepartmentDatabaseEntry> departmentLit = departmentDao.findAll();
        Assert.assertEquals(2, departmentLit.size());
        departmentDao.saveAndFlush(DepartmentDatabaseEntry.builder()
                .departmentId(3)
                .departmentName("test_department")
                .build());
        departmentLit = departmentDao.findAll();
        Assert.assertEquals(3, departmentLit.size());
        departmentLit.stream().forEach(System.out::println);
    }

    @Test
    public void testToDeleteDepartment() {
        departmentDao.delete(DepartmentDatabaseEntry.builder()
                .departmentId(2)
                .departmentName("Management")
                .build());
        List<DepartmentDatabaseEntry> departmentList = departmentDao.findAll();
        Assert.assertEquals(1, departmentList.size());
        departmentList.stream().forEach(System.out::println);
    }

    @Test
    public void testToGetById() {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(1);
        Assert.assertTrue(departmentDatabaseEntry.isPresent());
    }
}