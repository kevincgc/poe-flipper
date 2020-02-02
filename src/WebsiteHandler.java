import java.io.IOException;
import java.time.Instant;

import org.openqa.selenium.Cookie;

public class WebsiteHandler {
	private static WebsiteInterface w = new WebsiteInterface(); //limit to 1 instance of headless chrome running
	
	public WebsiteHandler(String sessionId) throws InterruptedException {
		//login to account
		w.goTo("https://www.pathofexile.com/");
		Cookie cookie = new Cookie.Builder("POESESSID", sessionId)
				.domain(".pathofexile.com")
				.path("/")
				.isSecure(true)
				.build();
		w.addCookie(cookie);
		//Thread.sleep(100);
	}
	
	public boolean parse(Currency currency) throws InterruptedException, IOException {
		//go to site and parse buy/sell data
		w.goTo(currency.getBuyLink());
		Thread.sleep(2000);
		parseCurrency(currency, Transaction.BUY);
		w.goTo(currency.getSellLink());
		Thread.sleep(2000);
		parseCurrency(currency, Transaction.SELL);
		
		//update currency
		currency.setHasParsed();
		currency.setOutdated(false);
		currency.setLastUpdated(Instant.now().getEpochSecond());
		
		return true;
	}
	
	private void parseCurrency(Currency currency, Transaction t) {
		double[] price = new double[20]; 
		double[] stock = new double[20];
		
		//get # of sellers
		int results = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		results = (results > 20) ? 20 : results;
		if (t == Transaction.BUY) {
			currency.setBuyResults(results);
		} else {
			currency.setSellResults(results);
		}
		
		//store array of results
		for (int i = 0; i < results; i++) {
			price[i] = Double.parseDouble(w.getText((t == Transaction.BUY) ? Xpath.BUY[i] : Xpath.SELL[i]));
			stock[i] = Double.parseDouble(w.getText(Xpath.AMOUNT[i]));
		}
		if (t == Transaction.BUY) {
			currency.setBuyPrice(price);
			currency.setBuyStock(stock);
		} else {
			currency.setSellPrice(price);
			currency.setSellStock(stock);
		}
	}
	
	/**
	 * Go to shop thread, take a screenshot to troubleshoot, enter text and submit, then refresh poe.trade.
	 * @param listing, the contents of the shop thread
	 * @return bool, execution has completed
	 */
	public boolean updateForum (String listing) throws InterruptedException, IOException {
		w.goTo("https://www.pathofexile.com/forum/edit-thread/2753867");
		Thread.sleep(1500);
		w.takeScreenshot("forum");
		w.submitForum(listing);
		Thread.sleep(1000);
		w.goTo("https://verify.poe.trade/2753867/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Thread.sleep(1000);
		
		return true;
	}
	
	public void exit() {
		w.stopServer();
	}
}
