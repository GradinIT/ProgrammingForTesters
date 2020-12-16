package se.jensen.exercise.department;



import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
//import se.jensen.exercise.DepartmentTestBuilder;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;
import java.util.Optional;
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
    public class DepartmentDaoTest {
        @Autowired
        private DepartmentDao departmentDao;
        @Test
        public void a_testFindAllDepartment(){
            List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());

            Assert.assertNotNull(departments);

        }
        @Test
        public void b_testFindDepartmentById(){
            Integer departmentId = Integer.valueOf(1);
            Department department = DepartmentDatabaseEntryMapper.map(departmentDao.findById(departmentId).get());
            Assert.assertNotNull(department);
            Assert.assertEquals(departmentId,department.getDepartmentId());
        }
        @Test
        public void testSaveNewDepartment(){
            Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(
                    DepartmentTestBuilder.build())));

            List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
            Assert.assertNotNull(department);
        }
        @Test
        public void c_testDeleteDepartment(){
            List<Department> departmentsBeforeDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
            System.out.println("\nDepartment before delete");
            departmentsBeforeDelete.stream().forEach(System.out::println);
            departmentDao.delete(DepartmentDatabaseEntryMapper.map(Department.builder()
                    .departmentId(5)
                    .departmentName("Engineer")
                    .build()));
            List<Department> departmentsAfterDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
            System.out.println("Departments after delete");
            departmentsAfterDelete.stream().forEach(System.out::println);
            Assert.assertEquals(3,departmentsAfterDelete.size());

        }
    }


