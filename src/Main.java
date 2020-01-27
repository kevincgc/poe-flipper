import java.io.IOException;

public class Main {
	static CurrencyRates c;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		c = new CurrencyRates();
		String currency, forumPost = "[spoiler]";
		String[] currencies = {"wis", "port", "aug", "tra", "whe", "scr", "p", "blessed", "regal",
				"alt", "fuse", "jew", "alch", "gcp", "chrom", "chance", "chisel", "scour",
				"divine", "vaal", "exa", "regret", "ba", "silver"
		};
		print("======================running..");
		
		for(String c: currencies) {
			while (!checkRate(c)) {};
		}
		
		c.postForum("c5f3823727b78c87a57178006d0662f3");
		c.updateForum("test1234");
		
		
		c.stopServer();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
	
	public static boolean checkRate (String currency) throws InterruptedException, IOException {
		c.parse(currency);
		while(!c.isHasParsed()) {}
		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
		System.out.println();
		return true;
	}
}