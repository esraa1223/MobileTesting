import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();
	AndroidDriver driver;
	Random rand = new Random();

	@BeforeTest
	public void Setup() {
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");
		File Myapplication = new File("src/Myapplication/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, Myapplication.getAbsolutePath());
	}

	@Test(priority = 1, enabled = false)
	public void MultiplyTwoNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		String ActualValue = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		Assert.assertEquals(ActualValue, "63");

	}

	@Test(priority = 2, enabled = false)
	public void ClickOnALlItems() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < AllButtons.size(); i++) {
			AllButtons.get(i).click();
		}

	}

	@Test(priority = 3, enabled = false)
	public void ClicknlyOnNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				AllButtons.get(i).click();
			}
		}

	}

	@Test(priority = 4, enabled = false)
	public void ClicknlyOnEvenNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				int value = Integer.parseInt(AllButtons.get(i).getDomAttribute("content-desc"));
				if (value % 2 == 0) {
					AllButtons.get(i).click();
				}
			}
		}

	}

	@Test(priority = 5, enabled = false)
	public void ClicknlyOnOddNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				int value = Integer.parseInt(AllButtons.get(i).getDomAttribute("content-desc"));
				if (value % 2 == 1) {
					AllButtons.get(i).click();
				}
			}
		}

	}

	@Test(priority = 6)
	public void MultiplyTwoRandomNumbers() throws MalformedURLException, InterruptedException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		String FirstNumber = Integer.toString(rand.nextInt(10));
		String SecondNumber = Integer.toString(rand.nextInt(10));

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		Thread.sleep(3000);
		for (int i = 0; i < AllButtons.size(); i++) {
			if(AllButtons.get(i).getDomAttribute("resource-id").contains("digit") && AllButtons.get(i).getDomAttribute("resource-id").contains(FirstNumber) ) {
				AllButtons.get(i).click();
			}
		
			if(AllButtons.get(i).getDomAttribute("resource-id").contains("digit") && AllButtons.get(i).getDomAttribute("resource-id").contains(SecondNumber)) {
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
				AllButtons.get(i).click();
			}


		}

	}
}
