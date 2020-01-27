import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

public class FxRates {

	public static void main(String[] args) throws IOException, InterruptedException {
		String chromeDriverPath = "./bin/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors",
				"--silent");
		WebDriver driver = new ChromeDriver(options);
		print("======================running..");

		// Get the login page
		driver.get("https://www.pathofexile.com/trade/exchange/Metamorph/dZk3cJ");
		Thread.sleep(1200);

//      // Search for username / password input and fill the inputs
//      driver.findElement(By.xpath("//input[@name='acct']")).sendKeys(userName);
//      driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
//        
//      // Locate the login button and click on it
//      driver.findElement(By.xpath("//input[@value='login']")).click();
//        
//      if(driver.getCurrentUrl().equals("https://news.ycombinator.com/login")){
//       	System.out.println("Incorrect credentials");
//       	driver.quit();
//       	System.exit(1);
//      }else{
//       	System.out.println("Successfuly logged in");
//      }
//      
		
		
		print(driver.findElement(By.xpath("//*[@id=\"trade\"]/div[6]/div[2]/div[1]/div[1]/div/div[2]/span[3]/span[1]")).getText());
		// Take a screenshot of the current page
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot, new File("screenshot.png"));

		// Logout
		// driver.findElement(By.id("logout")).click();
		driver.quit();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}