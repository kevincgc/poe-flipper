import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

public class WebsiteInterface {
	private final String chromeDriverPath = "./bin/chromedriver.exe";
	ChromeOptions options;
	WebDriver driver;

	public WebsiteInterface() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors",
				"--silent");
		driver = new ChromeDriver(options);
	}

	public void stopServer() {
		driver.quit();
	}

	public void takeScreenshot (String fileName) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot, new File(fileName + ".png"));
	}
	
	public String getText (String xPath) {
		return driver.findElement(By.xpath(xPath)).getText();
	}
	
	public void goTo (String site) throws InterruptedException {
		driver.get(site);
	}
}
