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
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;





    @Test
    public void TestDaoSaveDepartment(){

        DepartmentDatabaseEntry deparment = departmentDao.save(DepartmentDatabaseEntry.builder()
                        .departmentId(4)
                        .departmentName("Devops").build());

        Optional<DepartmentDatabaseEntry> deparetmentaerte = departmentDao.findById(4);

        Assert.assertNotNull(deparetmentaerte);
        Assert.assertEquals(deparment.getDepartmentId(), deparetmentaerte.get().getDepartmentId());
    }

    @Test
    public void TestFindDepartmentById(){

        Department depExpected = DepartmentDatabaseEntryMapper.map(departmentDao.save(
                DepartmentDatabaseEntryMapper.map(DepartmentTestBuilder.builder()
                        .departmentId(2)
                        .departmentName("Devops")
                        .build())));


        Optional <DepartmentDatabaseEntry> depOptional = departmentDao.findById(depExpected.getDepartmentId());
        DepartmentDatabaseEntry depDatabaseE = depOptional.get();
        Department depFoundId = DepartmentDatabaseEntryMapper.map(depDatabaseE);

        Assert.assertNotNull(depExpected);
        Assert.assertNotNull(depOptional);
        Assert.assertNotNull(depFoundId);

        Assert.assertEquals(depExpected,depFoundId);

    }


   @Test
    public void TestDeleteDepartment(){

        Optional<DepartmentDatabaseEntry> depExpected = departmentDao.findById(1);

        departmentDao.delete(depExpected.get());

        List <DepartmentDatabaseEntry> allDeparments = departmentDao.findAll();

        Assert.assertEquals(2, allDeparments.size());




    }



}
