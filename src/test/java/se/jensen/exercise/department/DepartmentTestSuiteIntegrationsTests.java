package se.jensen.exercise.department;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import se.jensen.test.category.IntegrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(IntegrationTest.class)
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
        TestDepartmentTestBuilder.class,
        DepartmentDaoTest.class,
        DepartmentRestApiTest.class
})
public class DepartmentTestSuiteIntegrationsTests {

}
