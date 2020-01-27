
public class CurrencyRates {
	WebsiteInterface w;
	
	public CurrencyRates() {
		w = new WebsiteInterface();
	}
	
	public void stopServer() {
		w.stopServer();
	}
	
	public void parse (String currency) {
		public static double checkProfit(String currency) throws InterruptedException {
			String buy, sell;
			double buyPrice, sellPrice, profit;
			switch (currency) {
			case "wis":
				buy = Links.BUY_WIS;
				sell = Links.SELL_WIS;
				break;
			case "port":
				buy = Links.BUY_PORT;
				sell = Links.SELL_PORT;
				break;
			default:
				buy = "";
				sell = "";
			}
			w.goTo(buy);
			Thread.sleep(2000);
			buyPrice = Double.parseDouble(w.getText(Xpath.BUY[0]));
			w.goTo(sell);
			Thread.sleep(2000);
			sellPrice = Double.parseDouble(w.getText(Xpath.SELL[0]));
			profit = buyPrice - sellPrice;
			return (profit / sellPrice) * 100;
		}
	}
}
