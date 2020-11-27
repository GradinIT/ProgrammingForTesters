package se.jensen.exercise.department;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestDepartmentService {
    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService service = new DepartmentServiceImpl();

    private final String DEPARTMENTNAME= "departname";
    private final Integer DEPARTMENTID = 1;
 void setUp(){
     MockitoAnnotations.initMocks(this);
     DepartmentDatabaseEntry departmentDatabaseEntry= DepartmentDatabaseEntry.builder()
              .departmentName(DEPARTMENTNAME)
             .departmentId(DEPARTMENTID)
             .build();
     List<DepartmentDatabaseEntry> list= new ArrayList<>();
     list.add(departmentDatabaseEntry);
 }

}
