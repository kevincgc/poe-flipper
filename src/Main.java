import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		CurrencyRates c = new CurrencyRates();
		String currency;
		print("======================running..");
		
		currency = "wis";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();

		currency = "port";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "aug";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "tra";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "blessed";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "whe";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "scr";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "p";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		
		currency = "regal";
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();

		c.stopServer();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}