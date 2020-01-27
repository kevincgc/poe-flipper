import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		CurrencyRates wis = new CurrencyRates("wis");
		CurrencyRates port = new CurrencyRates("port");
		print("======================running..");
		
		while(!wis.isHasParsed() || !port.isHasParsed()) {
			
		}
		
		print(String.valueOf(wis.getProfitRatio()));
		print(String.valueOf(port.getProfitRatio()));
		print("Results: " + port.getResults());
		

		print("======================done..");

		// Get the login page

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

		// Logout
		// driver.findElement(By.id("logout")).click();
	}

	public static void print(String string) {
		System.out.println(string);
	}
}