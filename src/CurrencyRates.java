
public class CurrencyRates {
	private static WebsiteInterface w = new WebsiteInterface();
	private String buy, sell;
	private double[] buyPrice, sellPrice, buyAmount, sellAmount;
	private double profit, profitRatio;
	private boolean hasParsed;
	private int results;
	
	public CurrencyRates() throws InterruptedException {
		hasParsed = false;
		buyPrice = new double[20]; buyAmount = new double[20];
		sellPrice = new double[20]; sellAmount = new double[20];
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
	
	public void parse (String currency) throws InterruptedException {
		hasParsed = false;
		setCurrency(currency);
		
		w.goTo(buy);
		Thread.sleep(1500);
		results = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		results = (results > 20) ? 20 : results;
		for(int i = 0; i < results; i++) {
			buyPrice[i] = Double.parseDouble(w.getText(Xpath.BUY[i]));
			buyAmount[i] = Double.parseDouble(w.getText(Xpath.AMOUNT[i]));
		}
		
		
		w.goTo(sell);
		Thread.sleep(1500);
		results = Integer.parseInt(w.getText(Xpath.RESULTS).replaceAll("[\\D]", ""));
		results = (results > 20) ? 20 : results;
		for(int i = 0; i < results; i++) {
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
		default:
			buy = "";
			sell = "";
		}
	}
}
