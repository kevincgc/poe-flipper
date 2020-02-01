
public class Currency {
	private final String name;
	private final String buyLink, sellLink;
	private int buyResults, sellResults;
	private double[] buyPrice, sellPrice, buyAmount, sellAmount;
	private double profit, profitRatio;
	private boolean hasParsed, isOutdated;
	private long lastUpdated;
	
	public Currency (String name, String buyLink, String sellLink) {
		this.name = name;
		this.buyLink = buyLink;
		this.sellLink = sellLink;
		hasParsed = false;
		isOutdated = true;
		buyPrice = new double[20];
		sellPrice = new double[20];
		buyAmount = new double[20];
		sellAmount = new double[20];
	}
	
	public void calcProfit() {
		profit = buyPrice[0] - sellPrice[0];
		profitRatio = (profit / sellPrice[0]) * 100;
	}
	
	
	//==========================get/set===================================

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public boolean isOutdated() {
		return isOutdated;
	}

	public void setOutdated(boolean isOutdated) {
		this.isOutdated = isOutdated;
	}

	public void setBuyPrice(double[] buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setSellPrice(double[] sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setBuyAmount(double[] buyAmount) {
		this.buyAmount = buyAmount;
	}

	public void setSellAmount(double[] sellAmount) {
		this.sellAmount = sellAmount;
	}

	public double getProfit() {
		return profit;
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public boolean isHasParsed() {
		return hasParsed;
	}

	public void setHasParsed() {
		this.hasParsed = true;
	}

	public int getBuyResults() {
		return buyResults;
	}

	public void setBuyResults(int buyResults) {
		this.buyResults = buyResults;
	}

	public int getSellResults() {
		return sellResults;
	}

	public void setSellResults(int sellResults) {
		this.sellResults = sellResults;
	}

	public String getName() {
		return name;
	}

	public String getBuyLink() {
		return buyLink;
	}

	public String getSellLink() {
		return sellLink;
	}
}
