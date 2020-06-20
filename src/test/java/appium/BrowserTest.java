package appium;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import TestUtility.AppiumUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BrowserTest {

	@Test
	public void testBrowser() throws FileNotFoundException {
		AppiumUtility utility = new AppiumUtility(
				System.getProperty("user.dir") + "\\resources\\properties\\DesiredCapabilities.properties");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", utility.getData("deviceName"));
		desiredCapabilities.setCapability("platformName", utility.getData("platformName"));
		desiredCapabilities.setCapability("platformVersion", utility.getData("platformVersion"));
		desiredCapabilities.setCapability("automationName", utility.getData("automationName"));
		desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, utility.getData("browserName"));
		desiredCapabilities.setCapability("chromedriverExecutable",
				System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
		AndroidDriver<MobileElement> driver = null;
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to("http://appium.io/");
		driver.findElement(By.xpath("//a[@class='navbar-brand']//parent::div//button")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Introduction')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Appium Philosophy')]")).click();
	}
}
