import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;

public class WebsiteHandler {
	private static WebsiteInterface w = new WebsiteInterface(); // limit to 1 instance of headless chrome running

	public void initiateHandler() throws InterruptedException {
		w.initiateInterface();
		w.goTo("https://www.pathofexile.com/");
		Cookie cookie = new Cookie.Builder("POESESSID", Defines.POESESSID).domain(".pathofexile.com").path("/").isSecure(true)
				.build();
		w.addCookie(cookie);
	}
	
	public void parse(Currency currency) throws InterruptedException, IOException {
		int count = 0;
		int sleepDelay = 1500;
		while (true) {
			try {
				// go to site and parse buy/sell data
				w.goTo(currency.getBuyLink());
				Thread.sleep(sleepDelay);
				parseCurrency(currency, Transaction.BUY);
				w.goTo(currency.getSellLink());
				Thread.sleep(sleepDelay);
				parseCurrency(currency, Transaction.SELL);
				break;
			} catch (NoSuchElementException e) {
				sleepDelay += 500;
				System.out.println("NoSuchElementException in " + currency.getName() + ", retrying..");
				if (++count == 2) {
					System.out.println("NoSuchElementException in " + currency.getName() + ", exiting..");
					exit();
				}
			}
		}

		// update currency
		currency.updateIndex();
		currency.setHasParsed();
		currency.setOutdated(false);
		currency.setLastUpdated(Instant.now().getEpochSecond());
	}

	private void parseCurrency(Currency currency, Transaction t) {
		double[] price = new double[20];
		double[] stock = new double[20];

		// get # of sellers
		int results = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		results = (results > 20) ? 20 : results;
		if (t == Transaction.BUY) {
			currency.setBuyResults(results);
		} else {
			currency.setSellResults(results);
		}

		// store array of results
		for (int i = 0; i < results; i++) {
			price[i] = Double.parseDouble(w.getText((t == Transaction.BUY) ? Xpath.BUY[i] : Xpath.SELL[i]));
			stock[i] = Double.parseDouble(w.getText(Xpath.AMOUNT[i]));
		}
		if (t == Transaction.BUY) {
			currency.setBuyPriceArr(price);
			currency.setBuyStockArr(stock);
		} else {
			currency.setSellPriceArr(price);
			currency.setSellStockArr(stock);
		}
	}

	public String updateShopThread(ArrayList<Currency> currencies, int size) throws InterruptedException, IOException {
		String text = "[spoiler]";
		// buy
		for (int i = 0; i < size; i++) {
			text += "[spoiler=\" ~b/o " + (int) (currencies.get(i).getBuyPrice() * 10) + "/10 "
					+ currencies.get(i).getName() + "\"][linkItem location=\"Stash18\" league=\"Metamorph\" x=\""
					+ currencies.get(i).getBuyX() + "\" y=\"" + currencies.get(i).getBuyY() + "\"][/spoiler]";
		}
		// sell
		for (int i = 0; i < size; i++) {
			text += "[spoiler=\" ~b/o  10/" + (int) (currencies.get(i).getSellPrice() * 10)
					+ " chaos\"][linkItem location=\"Stash18\" league=\"Metamorph\" x=\"" + currencies.get(i).getSellX()
					+ "\" y=\"" + currencies.get(i).getSellY() + "\"][/spoiler]";
		}
		text += "[/spoiler]";
		
		updateForum(text);
		
		return text;
	}
	
	/**
	 * Go to shop thread, take a screenshot to troubleshoot, enter text and submit,
	 * then refresh poe.trade.
	 */
	public void updateForum(String listing) throws InterruptedException, IOException {
		int count = 0;
		int sleepDelay = 1000;
		while (true) {
			try {
				w.goTo("https://www.pathofexile.com/forum/edit-thread/2753867");
				Thread.sleep(sleepDelay);
				//w.takeScreenshot("forum");
				w.submitForum(listing);
				Thread.sleep(sleepDelay);
				w.goTo("https://verify.poe.trade/2753867/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				Thread.sleep(sleepDelay);
				break;
			} catch (NoSuchElementException e) {
				sleepDelay += 500;
				System.out.println("NoSuchElementException updating forum, retrying..");
				if (++count == 2) {
					System.out.println("NoSuchElementException updating forum, exiting..");
					exit();
				}
			}
		}

	}

	public void exit() {
		w.stopServer();
	}
}
