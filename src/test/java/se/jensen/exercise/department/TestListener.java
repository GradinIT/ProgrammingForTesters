package se.jensen.exercise.department;

import se.jensen.util.Colour;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.awt.*;

public class TestListener extends RunListener {


    public static void main(String[] args) {

        System.out.println(Colour.RED + "\n-----TestListener-----\n" + Colour.NO);
        JUnitCore core = new JUnitCore();
        core.addListener(new TestListener());
        core.run(DepartmentTestSuite.class);
        System.out.println(Colour.RED + "\n-----End of testListener-----\n" + Colour.NO);
    }

    //This method runs before all test -------------------------------------------------
    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println(Colour.RED + "\nTests run" + description.getClassName() + Colour.NO);
    }

    //This method runs before every method -------------------------------------------------
    @Override
    public void testStarted (Description description) throws Exception
    {
        System.out.println(Colour.YELLOW + "\nTestListener - test started: "  + Colour.CYAN + description + Colour.NO);
    }

    //This method runs after every test method -------------------------------------------------
    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println(Colour.YELLOW + "Test listener - test finished: " + Colour.CYAN + description + Colour.NO);
        System.out.println("--------------------------------------");
    }

    //This method runs if test method is failed -------------------------------------------------
    @Override
    public void testFailure (Failure failure) throws Exception
    {
        System.out.println(Colour.YELLOW + "\nTestListener test failure "  + Colour.RED + failure + Colour.NO);
    }

    //This method runs if test method is ignored -------------------------------------------------
    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println(Colour.YELLOW + "Test ignored: " + Colour.GREEN + description + Colour.NO);
        System.out.println("--------------------------------------");
    }

    //This method runs after all test methods -------------------------------------------------
    @Override
    public void testRunFinished(Result result) throws Exception {

        System.out.println(Colour.BLUE + "\nResult of the test run: " + Colour.YELLOW + result.wasSuccessful() + Colour.NO);
        System.out.println(Colour.BLUE +"Run time: " + Colour.YELLOW + result.getRunTime() + " ms"+ Colour.NO);
        System.out.println(Colour.BLUE +"Run count: " +Colour.YELLOW + result.getRunCount() + Colour.YELLOW + Colour.NO);
        System.out.println(Colour.BLUE +"Failure count: " + Colour.YELLOW + result.getFailureCount()  + Colour.NO);
        System.out.println(Colour.BLUE +"Ignored count: " + Colour.YELLOW + result.getIgnoreCount()  + Colour.NO);
    }

}
