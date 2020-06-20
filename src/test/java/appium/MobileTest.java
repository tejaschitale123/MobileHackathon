package appium;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import TestUtility.AppiumUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileTest {

	@Test
	public void testApp() throws FileNotFoundException {
		AppiumUtility utility = new AppiumUtility(
				System.getProperty("user.dir") + "\\resources\\properties\\DesiredCapabilities.properties");

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", utility.getData("deviceName"));
		desiredCapabilities.setCapability("platformName", utility.getData("platformName"));
		desiredCapabilities.setCapability("platformVersion", utility.getData("platformVersion"));
		desiredCapabilities.setCapability("automationName", utility.getData("automationName"));
		desiredCapabilities.setCapability("appPackage", utility.getData("appPackage"));
		desiredCapabilities.setCapability("appActivity", utility.getData("appActivity"));

		AndroidDriver<MobileElement> driver = null;
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.ImageView[@content-desc='Search']").click();
		driver.findElementById("com.google.android.youtube:id/search_edit_text").sendKeys("Appium Demo");
		driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		driver.findElementsByXPath("//android.view.ViewGroup[@index='2']").get(1).click();
		utility.scrollToVisibleText(driver, "Read more");
		driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Sort comments\"]").click();
		driver.findElementByXPath(
				"	/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]")
				.click();

		int numberOfComments = Integer
				.parseInt(driver.findElementById("com.google.android.youtube:id/comments_count").getText());
		if (numberOfComments > 0) {
			int size = driver.findElementsById("com.google.android.youtube:id/comment_content").size();
			System.out.println("Comments are available");
			System.out.println("First Comment : "
					+ driver.findElementsById("com.google.android.youtube:id/comment_content").get(size - 1).getText());
		} else {
			System.out.println("There are no comments available");
		}

		driver.quit();
	}
}
