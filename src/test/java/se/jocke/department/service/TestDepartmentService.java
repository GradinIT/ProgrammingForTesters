package se.jocke.department.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    private DepartmentDao departmentDao = Mockito.mock(DepartmentDao.class);
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() {
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Development")
                .build()));
        Department department = systemUnderTest.getDepartmentById(1);
        Assertions.assertAll(
                () -> assertEquals(1,department.getDepartmentId()),
                () -> assertEquals("Development",department.getDepartmentName())
        );
    }
}
