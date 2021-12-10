package se.jocke.department.unittest.dao;

import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.config.PersistenceConfig;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, PersistenceConfig.class})
public class TestDepartmentDao {
    private final DepartmentDatabaseEntry ENTRY = DepartmentDatabaseEntryTestBuilder.build();

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void testGetDepartmentById() {
        departmentDao.save(ENTRY);
        Optional<DepartmentDatabaseEntry> optionalEmployeeDatabaseEntry = departmentDao.findById(ENTRY.getDepartmentId());
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(ENTRY.getDepartmentName(), optionalEmployeeDatabaseEntry.get().getDepartmentName()),
                () -> assertEquals(ENTRY.getDepartmentId(), optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
        departmentDao.delete(ENTRY);
    }

    @Test
    public void testGetDepartments() {
        List<DepartmentDatabaseEntry> departments = departmentDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(departments),
                () -> assertEquals(6, departments.size())
        );
    }

    @Test
    public void testStoreDepartment() {
        departmentDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, departmentDao.findById(ENTRY.getDepartmentId()).isPresent()),
                () -> assertEquals(ENTRY, departmentDao.findById(ENTRY.getDepartmentId()).get())
        );
        departmentDao.delete(ENTRY);
    }
    @Test
    public void testDeleteDepartment() throws InterruptedException {
        departmentDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, departmentDao.findById(ENTRY.getDepartmentId()).isPresent()),
                () -> assertEquals(ENTRY, departmentDao.findById(ENTRY.getDepartmentId()).get())
        );
        departmentDao.delete(ENTRY);
        Assertions.assertEquals(Boolean.TRUE, departmentDao.findById(ENTRY.getDepartmentId()).isEmpty());
    }
    @Test
    public void testUpdateDepartment() {
        departmentDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, departmentDao.findById(ENTRY.getDepartmentId()).isPresent()),
                () -> assertEquals(ENTRY, departmentDao.findById(ENTRY.getDepartmentId()).get())
        );
        DepartmentDatabaseEntry update = DepartmentDatabaseEntry.builder()
                .departmentId(ENTRY.getDepartmentId())
                .departmentName("Tok")
                .build();
        departmentDao.save(update);
        DepartmentDatabaseEntry updated = departmentDao.findById(ENTRY.getDepartmentId()).get();
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, departmentDao.findById(ENTRY.getDepartmentId()).isPresent()),
                () -> assertEquals(update, updated)
        );
        departmentDao.delete(updated);
    }
}
