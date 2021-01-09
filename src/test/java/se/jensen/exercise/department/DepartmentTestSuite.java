package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class,
        TestDepartmentMapper.class,
        TestDepartmentModel.class,
        TestDepartmentModelsmapper.class,
        TestThatDepartmentIsStoredInDatabase.class,

        TestDepartmentDataBaseEntry.class,

        DepartmentDaoTest.class,

        DaoDepartmentFindAll.class

})
public class DepartmentTestSuite {
}
