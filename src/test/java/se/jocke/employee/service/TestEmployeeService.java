package se.jocke.employee.service;

public class TestEmployeeService {
}


/*

@ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    @Mock
    private DepartmentDao departmentDao;
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();

    @BeforeEach
    public void setUp() {
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
        verify(departmentDao, times(1)).findById(1);
    }
}

 */