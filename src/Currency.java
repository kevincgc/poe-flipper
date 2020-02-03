
public class Currency implements Comparable<Currency> {
	// get
	private final String name;
	private final String buyLink, sellLink;
	private double profitRatio, buyPrice, sellPrice;
	// set
	private double[] buyPriceArr, sellPriceArr, buyStockArr, sellStockArr;
	private int buyX, buyY, sellX, sellY, minStock, offset;
	private double overrideSellPrice, overrideBuyPrice;
	// get+set
	private int buyResults, sellResults;
	private boolean hasParsed, isOutdated;
	private long lastUpdated;
	// internal
	private int buyIndex, sellIndex;

	public Currency(String name, String buyLink, String sellLink) {
		this.name = name;
		this.buyLink = buyLink;
		this.sellLink = sellLink;
		hasParsed = false;
		isOutdated = true;
		buyPriceArr = new double[20];
		sellPriceArr = new double[20];
		buyStockArr = new double[20];
		sellStockArr = new double[20];
		minStock = Defines.DEFAULT_MINIMUM_STOCK;
		offset = 0;
		overrideSellPrice = 0;
		overrideBuyPrice = 0;
	}

	public double calcProfit() {
		double profit = buyPriceArr[buyIndex] - sellPriceArr[sellIndex];
		profitRatio = Math.round((profit / sellPriceArr[sellIndex]) * 1000) / 10;
		return profitRatio;
	}

	public double calcBuyPrice() {
		if (overrideBuyPrice == 0) {
			if (buyResults >= 10 && buyIndex == 0) {
				buyPrice = buyPriceArr[3];
			} else if (buyResults - buyIndex <= 2){
				buyPrice = buyPriceArr[buyIndex];
			} else {
				buyPrice = buyPriceArr[buyIndex + 1];
			}
		} else {
			buyPrice = overrideBuyPrice;
		}


		return buyPrice;
	}

	public double calcSellPrice() {
		if (overrideSellPrice == 0) {
			if (sellResults >= 20 && sellIndex <= 2) {
				sellPrice = sellPriceArr[sellIndex + 10];
			} else if (sellResults >= 20 && sellIndex <= 13) {
				sellPrice = sellPriceArr[sellIndex + 6];
			} else if (sellResults >= 20 && sellIndex > 13) {
				sellPrice = sellPriceArr[15];
			} else {
				sellPrice = sellPriceArr[sellIndex];
			}
		} else {
			sellPrice = overrideSellPrice;
		}

		return sellPrice;
	}

	public void updateIndex() {
		buyIndex = getMinStockIndex(buyStockArr, minStock);
		sellIndex = getMinStockIndex(sellStockArr, Defines.MINIMUM_BUYER_CHAOS);
	}

	// ==========================internal===================================

	private double getLowestPrice(final double[] price, final double[] amount, final double results, int minAmount,
			double maxDifferencePercentage) {
		double sum = 0, count = 0, differencePercentage;
		int index = 0;

		while (true) {
			for (int i = 1; i < (((results - index) < 5) ? (results - index) : 5); i++) {
				sum += price[i + index];
				count++;
			}
			double average = sum / count;
			// System.out.println("sum: " + sum + " count: " + count);
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

	private int getMinStockIndex(double[] stock, int minStock) {
		for (int i = 0; i < stock.length; i++) {
			if (stock[i] > minStock) {
				return i + offset;
			}
		}

		return stock.length - 1;
	}

	@Override
	public String toString() {
		calcProfit();
		String str = name + " - Minimum profit: " + profitRatio + "%   ";
		str += "Lowest buy: " + buyPriceArr[buyIndex] + " " + name + "/c   ";
		str += "Selected buy: " + buyPrice + " " + name + "/c   ";
		str += "Lowest sell: " + sellPriceArr[sellIndex] + " c/" + name;
		str += "Selected sell: " + sellPrice + " c/" + name;

		return str;
	}

	@Override
	public int compareTo(Currency o) {
		return this.profitRatio < o.profitRatio ? -1 : (this.profitRatio > o.profitRatio ? 1 : 0);
	}

	// ==========================get/set===================================
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setOverrideSellPrice(double overrideSellPrice) {
		this.overrideSellPrice = overrideSellPrice;
	}

	public void setOverrideBuyPrice(double overrideBuyPrice) {
		this.overrideBuyPrice = overrideBuyPrice;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public boolean isOutdated() {
		return isOutdated;
	}

	public void setOutdated(boolean isOutdated) {
		this.isOutdated = isOutdated;
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

	public void setBuyPriceArr(double[] buyPrice) {
		this.buyPriceArr = buyPrice;
	}

	public void setSellPriceArr(double[] sellPrice) {
		this.sellPriceArr = sellPrice;
	}

	public void setBuyStockArr(double[] buyAmount) {
		this.buyStockArr = buyAmount;
	}

	public void setSellStockArr(double[] sellAmount) {
		this.sellStockArr = sellAmount;
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

	public void setBuyX(int buyX) {
		this.buyX = buyX;
	}

	public void setBuyY(int buyY) {
		this.buyY = buyY;
	}

	public void setSellX(int sellX) {
		this.sellX = sellX;
	}

	public void setSellY(int sellY) {
		this.sellY = sellY;
	}

	public int getBuyX() {
		return buyX;
	}

	public int getBuyY() {
		return buyY;
	}

	public int getSellX() {
		return sellX;
	}

	public int getSellY() {
		return sellY;
	}

}
