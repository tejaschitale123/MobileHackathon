package TestUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumUtility {

	static Properties properties;
	static String workingDirectory = System.getProperty("user.dir");

	public AppiumUtility(String FilePath) throws FileNotFoundException {

		FileInputStream Locator = new FileInputStream(FilePath);
		properties = new Properties();
		try {
			properties.load(Locator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getData(String ElementName) {

		String data = properties.getProperty(ElementName);
		return data;
	}

	/**
	 * This method waitforApptoload() helps with setting default wait time before
	 * next event
	 * 
	 * @param waitTime In seconds.
	 */
	public void waitforApptoload(int waitTime) {
		try {
			Thread.sleep(waitTime * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollToVisibleText(AndroidDriver<MobileElement> driver, String visibleText) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))");
	}

}
