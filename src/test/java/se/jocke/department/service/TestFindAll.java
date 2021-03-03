package se.jocke.department.service;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TestFindAll{

    @Mock
    private DepartmentDao departmentDao;
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();


  @BeforeEach
  public void setUp(){
      when(departmentDao.findAll()).thenReturn(List.of(DepartmentDatabaseEntry.builder()
              .departmentId(1)
              .departmentName("Development").build(), DepartmentDatabaseEntry.builder()
              .departmentId(2)
              .departmentName("Development2")
              .build())
      );
  }

@Test
    public void findAll() {
        List<Department> testlist = systemUnderTest.getDepartments();
        Assertions.assertEquals(2, testlist.size());
    }

}
