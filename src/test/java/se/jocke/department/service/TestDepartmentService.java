package se.jocke.department.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestDepartmentService {
    @Mock
    private DepartmentDao departmentDao;
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Development")
                .build()));
    }

    @Test
    public void findById() {
        Department department = systemUnderTest.getDepartmentById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, department.getDepartmentId()),
                () -> Assertions.assertEquals("Development", department.getDepartmentName())
        );
        verify(departmentDao,times(1)).findById(1);
    }
}
