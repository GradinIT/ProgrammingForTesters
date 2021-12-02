package se.jocke.department.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestDepartmentDao {
    @Autowired
    DepartmentDao departmentDao;

    /*
    @ParameterizedTest
    @MethodSource({"stringFields"})
    public void testGetDepartmentById(String parameter) {
        Integer departmetId = 99;
        Optional<DepartmentDatabaseEntry> optionalEmployeeDatabaseEntry = departmentDao.findById(departmetId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(parameter, optionalEmployeeDatabaseEntry.get().getDepartmentName()),
                () -> assertEquals(departmetId, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }

    static Stream<String> stringFields() {
        return Stream.of("Test_Development", "Development");
    }*/

    @Test
    public void testGetDepartmentById() {
        Integer departmetId = 99;
        Optional<DepartmentDatabaseEntry> optionalEmployeeDatabaseEntry = departmentDao.findById(departmetId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Department", optionalEmployeeDatabaseEntry.get().getDepartmentName()),
                () -> assertEquals(departmetId, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }

    @Test
    public void testGetDepartments() {
        List<DepartmentDatabaseEntry> departments = departmentDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(departments),
                () -> assertEquals(4, departments.size())
        );
    }
}
