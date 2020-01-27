
public class CurrencyRates {
	private WebsiteInterface w;
	private String buy, sell, results;
	private double[] buyPrice, sellPrice;
	private double profit, profitRatio;
	private boolean hasParsed;
	
	public CurrencyRates(String currency) throws InterruptedException {
		hasParsed = false;
		buyPrice = new double[20];
		sellPrice = new double[20];
		w = new WebsiteInterface();
		
		setCurrency(currency);
		
		w.goTo(buy);
		Thread.sleep(1500);
		buyPrice[0] = Double.parseDouble(w.getText(Xpath.BUY[0]));
		
		w.goTo(sell);
		Thread.sleep(1500);
		sellPrice[0] = Double.parseDouble(w.getText(Xpath.SELL[0]));
		
		results = w.getText(Xpath.RESULTS);
		profit = buyPrice[0] - sellPrice[0];
		profitRatio = (profit / sellPrice[0]) * 100;
		
		w.stopServer();
		hasParsed = true;
	}
	
	public String getResults() {
		return results;
	}

	public void stopServer() {
		w.stopServer();
	}

	public double getBuyPrice(int index) {
		return buyPrice[index];
	}

	public double getSellPrice(int index) {
		return sellPrice[index];
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public boolean isHasParsed() {
		return hasParsed;
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
		default:
			buy = "";
			sell = "";
		}
	}
}
