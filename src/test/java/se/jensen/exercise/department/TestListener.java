package se.jensen.exercise.department;

import se.jensen.util.Colour;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class TestListener extends RunListener {

    public static void main(String[] args) {

        System.out.println(Colour.RED + "\n-----TestListener-----\n" + Colour.NO);
        JUnitCore core = new JUnitCore();
        core.addListener(new TestListener());
        core.run(DepartmentTestSuite.class);
        System.out.println(Colour.RED + "\n-----End of testListener-----\n" + Colour.NO);
    }

//-----------------------------------------------------------------
    //Called before any tests have been run.
    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println(Colour.RED + "\nTests run  " + Colour.NO);
    }

//-----------------------------------------------------------------
    //Called when an atomic test is about to be started.
    @Override
    public void testStarted (Description description) throws Exception
    {
        System.out.println(Colour.YELLOW + "\nTestListener - test started: " + Colour.CYAN +
                description.getMethodName()
                + Colour.YELLOW + "  class: " + Colour.CYAN
                + description.getClassName() + Colour.NO);
    }

//-----------------------------------------------------------------
    // Called when an atomic test has finished, whether the test succeeds or fails.
    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println(Colour.YELLOW + "Test listener - tests finished: " + Colour.CYAN + description.getMethodName()
                + Colour.NO);
        System.out.println("--------------------------------------");
    }

//-----------------------------------------------------------------
    //Called when an atomic test fails.
    @Override
    public void testFailure (Failure failure) throws Exception
    {
        System.out.println(Colour.YELLOW + "\nTestListener tests failure "  + Colour.RED + failure + Colour.NO);
    }

//-----------------------------------------------------------------
    //Called when a test will not be run, generally because a test method is annotated with Ignore.
    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println(Colour.YELLOW + "Tests ignored: " + Colour.GREEN + description + Colour.NO);
        System.out.println("--------------------------------------");
    }

//-----------------------------------------------------------------
    //Called when all tests have finished
    @Override
    public void testRunFinished(Result result) throws Exception {

        System.out.println(Colour.BLUE + "\nResult of the test run: " + Colour.YELLOW + result.wasSuccessful() + Colour.NO);
        System.out.println(Colour.BLUE +"Run time: " + Colour.YELLOW + result.getRunTime() + " ms"+ Colour.NO);
        System.out.println(Colour.BLUE +"Number od tests executed: " +Colour.YELLOW + result.getRunCount() + Colour.YELLOW + Colour.NO);
        System.out.println(Colour.BLUE +"Failure count: " + Colour.YELLOW + result.getFailureCount()  + Colour.NO);
        System.out.println(Colour.BLUE +"Ignored count: " + Colour.YELLOW + result.getIgnoreCount()  + Colour.NO);
    }

}
