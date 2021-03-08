package se.jocke.department.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.*;
import se.jocke.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void findById() {

        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Development")
                .build()));

        Department department = systemUnderTest.getDepartmentById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, department.getDepartmentId()),
                () -> Assertions.assertEquals("Development", department.getDepartmentName())
        );
        verify(departmentDao, times(1)).findById(1);
    }

    @Test
    public void getAllDepartments() {
        when(departmentDao.findAll()).thenReturn(Arrays.asList(DepartmentDatabaseEntry.builder()
                .departmentName("Development")
                .departmentId(1)
                .build()));

        List<Department> departments = systemUnderTest.getDepartments();
        Assertions.assertAll(
                () -> assertNotNull(departments),
                () -> assertEquals(1, departments.size())
        );
    }

    @Test
    public void createDepartmentHappyFlow() {
        Department department = DepartmentTestBuilder.builder().build();
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(departmentDao.save(any(DepartmentDatabaseEntry.class))).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build());
        Department createdDepartment = systemUnderTest.create(department);
        Assertions.assertAll(
                () -> assertNotNull(createdDepartment),
                () -> assertEquals(department.getDepartmentId(), createdDepartment.getDepartmentId()),
                () -> assertEquals(department.getDepartmentName(), createdDepartment.getDepartmentName())
        );
        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any(DepartmentDatabaseEntry.class));
    }

    @Test
    public void createDepartmentError() {
        Department department = DepartmentTestBuilder.builder().build();
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(
                DepartmentDatabaseEntry.builder()
                    .departmentId(department.getDepartmentId())
                    .departmentName(department.getDepartmentName())
                    .build()
        ));
        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.create(department);
        });
        Assertions.assertEquals("Entity with id "+department.getDepartmentId()+" already in storage",
                exception.getMessage());
        verify(departmentDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(departmentDao);
    }
    @Test
    public void updateDepartmentHappyFlow(){
        Department department = DepartmentTestBuilder.builder().build();

        when(departmentDao.findById(any(Integer.class)))
                .thenReturn(Optional.of(DepartmentDatabaseEntryMapper.map(department)));
        when(departmentDao.save(DepartmentDatabaseEntryMapper.map(department)))
                .thenReturn(DepartmentDatabaseEntryMapper.map(department));
        Department d = systemUnderTest.update(department);
        Assertions.assertAll(
                () -> assertNotNull(d),
                () -> assertEquals(d.getDepartmentName(), department.getDepartmentName())
        );

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any(DepartmentDatabaseEntry.class));
    }

    @Test
    public void updateDepartmentError(){

      Department departmentToUpdate = DepartmentTestBuilder.builder().build();
      Integer departmentId = departmentToUpdate.getDepartmentId();

        when(departmentDao.findById(departmentId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                { systemUnderTest.update(departmentToUpdate); }
        );
        verify(departmentDao, times(1)).findById(departmentId);
        verifyNoMoreInteractions(departmentDao);
        Assertions.assertEquals("Entity with id "+ departmentId +" not found",exception.getMessage());
    }
    @Test
    public void removeDepartmentHappyFlow(){
        Department departmentToRemove = DepartmentTestBuilder.builder().build();
        when(departmentDao.findById(any(Integer.class)))
                .thenReturn(Optional.of(DepartmentDatabaseEntryMapper.map(departmentToRemove)));

        Assertions.assertEquals(departmentToRemove, systemUnderTest.remove(departmentToRemove));
        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).delete(DepartmentDatabaseEntryMapper.map(departmentToRemove));
    }

    @Test
    public void removeDepartmentError(){
        Department departmentToRemove = DepartmentTestBuilder.builder().build();
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.remove(departmentToRemove));
        verify(departmentDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(departmentDao);
    }
}
