
public class Currency {
	private final String name;
	private final String buyLink, sellLink;
	private int buyResults, sellResults, minStock;
	private double[] buyPrice, sellPrice, buyStock, sellStock;
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
		buyStock = new double[20];
		sellStock = new double[20];
		minStock = 1000;
	}
	
	public Currency (String name, String buyLink, String sellLink, int minAmount) {
		this(name, buyLink, sellLink);
		this.minStock = minAmount;
	}
	
	public double calcProfit() {
		int buyIndex = 0, sellIndex = 0;

		//filter out low stocks
		for (int i = 0; i < 20; i++) {
			if (buyStock[i] < 100) {
				buyIndex = i;
			} else {
				break;
			}
		}
		for (int i = 0; i < 20; i++) {
			if (sellStock[i] < minStock) {
				sellIndex = i;
			} else {
				break;
			}
		}
		
		System.out.println("Buy price: " + buyPrice[buyIndex] + " Sell price: " + sellPrice[sellIndex]);
		profit = buyPrice[buyIndex] - sellPrice[sellIndex];
		profitRatio = (profit / sellPrice[sellIndex]) * 100;
		return profitRatio;
	}
	
	public double calcBuyPrice(double maxDifferencePercentage) {
		return getLowestPrice(buyPrice, buyStock, buyResults, minStock, maxDifferencePercentage);
	}
	public double calcBuyPrice() {
		return calcBuyPrice(20);
	}
	
	/**
	 * Calculate the highest price to sell at, assuming buy has at least 40c.
	 * @param maxDifferencePercentage
	 * @return
	 */
	public double calcSellPrice(double maxDifferencePercentage) {
		return getLowestPrice(sellPrice, sellStock, sellResults, 40, maxDifferencePercentage);
	}
	public double calcSellPrice() {
		return calcSellPrice(20);
	}
	
	private double getLowestPrice(final double[] price, final double[] amount, final double results, int minAmount, double maxDifferencePercentage) {
		double sum = 0, count = 0, differencePercentage;
		int index = 0;

		//filter out low stocks
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

	public void setBuyStock(double[] buyAmount) {
		this.buyStock = buyAmount;
	}

	public void setSellStock(double[] sellAmount) {
		this.sellStock = sellAmount;
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
