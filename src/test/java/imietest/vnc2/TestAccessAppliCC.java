package imietest.vnc2;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true,
features="src/test/java/imietest/vnc2/TestAccessAppliCC.feature")
public class TestAccessAppliCC {


}
