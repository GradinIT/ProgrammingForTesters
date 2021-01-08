package se.jensen.exercise.department;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import se.jensen.test.category.UnitTest;

@RunWith(Categories.class)
@Categories.ExcludeCategory(UnitTest.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class,
        TestDepartmentModelCreated.class,
        TestDepartmentModelMapper.class,
        TestDepartmentModelsMapper.class,
        TestDepartmentDatabaseEntryCreated.class,
        TestDepartmentDatabaseEntryMapper.class,
        DepartmentDaoTest.class,
        DepartmentRestApiTest.class
})
public class DepartmentTestSuiteIntegrationsTests {

}