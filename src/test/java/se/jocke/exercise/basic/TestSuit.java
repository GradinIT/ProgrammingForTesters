package se.jocke.exercise.basic;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "se.jocke.exercise.basic"
})
public class TestSuit {
}
