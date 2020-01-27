import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		CurrencyRates c = new CurrencyRates();
		print("======================running..");
		
		c.parse("wis");
		while(!c.isHasParsed()) {}
		System.out.print("Wis: " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " wis/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 3000, 50) + " c/wis   ");
		System.out.println();

		c.parse("port");
		while(!c.isHasParsed()) {}
		System.out.print("Port: " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " port/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 3000, 50) + " c/port   ");
		System.out.println();

		c.stopServer();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}