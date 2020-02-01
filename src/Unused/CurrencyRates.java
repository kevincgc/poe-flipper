package Unused;
import java.io.IOException;

import org.openqa.selenium.Cookie;

import WebsiteInterface;
import Xpath;

public class CurrencyRates {
	private static WebsiteInterface w = new WebsiteInterface();
	private String buy, sell;
	private double[] buyPrice, sellPrice, buyAmount, sellAmount;
	private double profit, profitRatio;
	private boolean hasParsed;
	private int buyResults, sellResults;

	public CurrencyRates() throws InterruptedException {
		hasParsed = false;
		buyPrice = new double[20];
		buyAmount = new double[20];
		sellPrice = new double[20];
		sellAmount = new double[20];
	}

	public void stopServer() {
		w.stopServer();
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public boolean isHasParsed() {
		return hasParsed;
	}
	
	public void postForum (String sessionId) throws InterruptedException, IOException {
		w.goTo("https://www.pathofexile.com/forum/edit-thread/2753867");
		Cookie cookie = new Cookie.Builder("POESESSID", sessionId)
				.domain(".pathofexile.com")
				.path("/")
				.isSecure(true)
				.build();
		w.addCookie(cookie);
		Thread.sleep(100);
		w.goTo("https://www.pathofexile.com/forum/edit-thread/2753867");
		Thread.sleep(1500);
		w.takeScreenshot("forum");
	}
	
	public boolean updateForum (String text) throws InterruptedException {
		w.submitForum(text);
		Thread.sleep(1000);
		w.goTo("https://verify.poe.trade/2753867/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Thread.sleep(1500);
		return true;
	}

	public double getLowestPrice(String buyOrSell, int minAmount, double maxDifferencePercentage) {
		final double[] price, amount;
		double results, sum = 0, count = 0, differencePercentage;
		int index = 0;

		if (buyOrSell == "buy") {
			price = buyPrice;
			amount = buyAmount;
			results = buyResults;
		} else if (buyOrSell == "sell") {
			price = sellPrice;
			amount = sellAmount;
			results = sellResults;
		} else {
			return -1;
		}

		for (int i = 0; i < 20; i++) {
			if (amount[i] < minAmount) {
				index = i;
			} else {
				break;
			}
		}
		while (true) {
			for (int i = 1; i < (((results - index) < 5) ? (results - index) : 5); i++) {
				sum += price[i + index];
				count++;
			}
			double average = sum / count;
			//System.out.println("sum: " + sum + " count: " + count);
			differencePercentage = Math.abs(price[index] - average) / average * 100;
			if (differencePercentage > maxDifferencePercentage) {
				sum = 0;
				count = 0;
				index++;
			} else {
				break;
			}
		}

		return price[index];
	}

	public void parse(String currency) throws InterruptedException, IOException {
		hasParsed = false;
		setCurrency(currency);

		w.goTo(buy);
		Thread.sleep(1500);
		w.takeScreenshot("test1");
		buyResults = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		buyResults = (buyResults > 20) ? 20 : buyResults;
		for (int i = 0; i < buyResults; i++) {
			buyPrice[i] = Double.parseDouble(w.getText(Xpath.BUY[i]));
			buyAmount[i] = Double.parseDouble(w.getText(Xpath.AMOUNT[i]));
		}

		w.goTo(sell);
		Thread.sleep(1500);
		sellResults = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		sellResults = (sellResults > 20) ? 20 : sellResults;
		for (int i = 0; i < sellResults; i++) {
			sellPrice[i] = Double.parseDouble(w.getText(Xpath.SELL[i]));
			sellAmount[i] = Double.parseDouble(w.getText(Xpath.AMOUNT[i]));
		}

		profit = buyPrice[0] - sellPrice[0];
		profitRatio = (profit / sellPrice[0]) * 100;

		hasParsed = true;
	}

	private void setCurrency(String currency) {
		switch (currency) {
		case "wis":
			buy = Links.BUY_WIS;
			sell = Links.SELL_WIS;
			break;
		case "port":
			buy = Links.BUY_PORT;
			sell = Links.SELL_PORT;
			break;
		case "tra":
			buy = Links.BUY_TRA;
			sell = Links.SELL_TRA;
			break;
		case "aug":
			buy = Links.BUY_AUG;
			sell = Links.SELL_AUG;
			break;
		case "whe":
			buy = Links.BUY_WHE;
			sell = Links.SELL_WHE;
			break;
		case "scr":
			buy = Links.BUY_SCR;
			sell = Links.SELL_SCR;
			break;
		case "blessed":
			buy = Links.BUY_BLESSED;
			sell = Links.SELL_BLESSED;
			break;
		case "regal":
			buy = Links.BUY_REGAL;
			sell = Links.SELL_REGAL;
			break;
		case "p":
			buy = Links.BUY_P;
			sell = Links.SELL_P;
			break;
		case "alt":
			buy = Links.BUY_ALT;
			sell = Links.SELL_ALT;
			break;
		case "fuse":
			buy = Links.BUY_FUSE;
			sell = Links.SELL_FUSE;
			break;
		case "jew":
			buy = Links.BUY_JEW;
			sell = Links.SELL_JEW;
			break;
		case "alch":
			buy = Links.BUY_ALCH;
			sell = Links.SELL_ALCH;
			break;
		case "gcp":
			buy = Links.BUY_GCP;
			sell = Links.SELL_GCP;
			break;
		case "chrom":
			buy = Links.BUY_CHROM;
			sell = Links.SELL_CHROM;
			break;
		case "chance":
			buy = Links.BUY_CHANCE;
			sell = Links.SELL_CHANCE;
			break;
		case "chisel":
			buy = Links.BUY_CHISEL;
			sell = Links.SELL_CHISEL;
			break;
		case "scour":
			buy = Links.BUY_SCOUR;
			sell = Links.SELL_SCOUR;
			break;
		case "divine":
			buy = Links.BUY_DIVINE;
			sell = Links.SELL_DIVINE;
			break;
		case "vaal":
			buy = Links.BUY_VAAL;
			sell = Links.SELL_VAAL;
			break;
		case "exa":
			buy = Links.BUY_EXA;
			sell = Links.SELL_EXA;
			break;
		case "regret":
			buy = Links.BUY_REGRET;
			sell = Links.SELL_REGRET;
			break;
		case "ba":
			buy = Links.BUY_BA;
			sell = Links.SELL_BA;
			break;
		case "silver":
			buy = Links.BUY_SILVER;
			sell = Links.SELL_SILVER;
			break;			
		default:
			buy = "";
			sell = "";
		}
	}
}
