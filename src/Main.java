import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Currency> currencies;
	private static String POESESSID = "87499f6b8c0a84bf5c8a8e27c2074b71";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//initiate currency
		currencies = new ArrayList<Currency>();
		initiateCurrency();
		
		//initiate website
		WebsiteHandler handle = new WebsiteHandler(POESESSID);

		print("======================running..");
		for (int i = 0; i < 4; i++) {
			handle.parse(currencies.get(i));
			System.out.println(currencies.get(i).getName());
			System.out.println(currencies.get(i).calcProfit());
		}
		
		String forumPost = handle.updateShopThread(currencies, 4);
		System.out.println(forumPost);
		
//		for(Currency c: currencies) {
//			handle.parse(c);
//			
//		}
		
		handle.exit();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
	
//	public static boolean checkRate (String currency) throws InterruptedException, IOException {
//		c.parse(currency);
//		while(!c.isHasParsed()) {}
//		System.out.print(currency + ": " + String.valueOf(Math.round(c.getProfitRatio() * 10) / 10.0) + "%   ");
//		System.out.print("buy: " + c.getLowestPrice("buy", 100, 50) + " " + currency + "/c   ");
//		System.out.print("sell: " + c.getLowestPrice("sell", 1000, 16) + " c/" + currency + "   ");
//		System.out.println();
//		return true;
//	}
	
	private static Currency wis, port, aug, tra, whe, scr, p, blessed, regal, alt, fuse, jew, alch, gcp, 
		chrom, chance, chisel, scour, divine, vaal, regret, ba, silver, exa;
	private static void initiateCurrency () {
		//wisdom
		wis = new Currency("wis", "https://www.pathofexile.com/trade/exchange/Metamorph/JBVail",
				"https://www.pathofexile.com/trade/exchange/Metamorph/3qpGU5");
		currencies.add(wis);
		//portal
		port = new Currency("port", "https://www.pathofexile.com/trade/exchange/Metamorph/powWf0",
				"https://www.pathofexile.com/trade/exchange/Metamorph/bE5GcL");
		currencies.add(port);
		//transmutation
		tra = new Currency("tra", "https://www.pathofexile.com/trade/exchange/Metamorph/zbqvI4",
				"https://www.pathofexile.com/trade/exchange/Metamorph/PrBMcL");
		currencies.add(tra);
		//augmentation
		aug = new Currency("aug", "https://www.pathofexile.com/trade/exchange/Metamorph/dZk3cJ",
				"https://www.pathofexile.com/trade/exchange/Metamorph/5M2osa");
		currencies.add(aug);
		
		//whetstone
		whe = new Currency("whe", "https://www.pathofexile.com/trade/exchange/Metamorph/VLywup",
				"https://www.pathofexile.com/trade/exchange/Metamorph/KBWPi5");
		currencies.add(whe);
		//armorer
		scr = new Currency("scr", "https://www.pathofexile.com/trade/exchange/Metamorph/z942f4",
				"https://www.pathofexile.com/trade/exchange/Metamorph/v0eDfE");
		currencies.add(scr);
		//blessed
		blessed = new Currency("blessed", "https://www.pathofexile.com/trade/exchange/Metamorph/5n8nta",
				"https://www.pathofexile.com/trade/exchange/Metamorph/Q2Q7Cw");
		currencies.add(blessed);
		//regal
		regal = new Currency("regal", "https://www.pathofexile.com/trade/exchange/Metamorph/YpagsY",
				"https://www.pathofexile.com/trade/exchange/Metamorph/EBBVC5");
		currencies.add(regal);
		//perandus
		p = new Currency("p", "https://www.pathofexile.com/trade/exchange/Metamorph/243DIk",
				"https://www.pathofexile.com/trade/exchange/Metamorph/YpLRsY");
		currencies.add(p);
		
		//alteration
		alt = new Currency("alt", "https://www.pathofexile.com/trade/exchange/Metamorph/AjnbtX",
				"https://www.pathofexile.com/trade/exchange/Metamorph/yXeeiR");
		currencies.add(alt);
		//fusing
		fuse = new Currency("fuse", "https://www.pathofexile.com/trade/exchange/Metamorph/jW58VLUX",
				"https://www.pathofexile.com/trade/exchange/Metamorph/5M2osa");
		currencies.add(fuse);
		//jewellers
		jew = new Currency("jew", "https://www.pathofexile.com/trade/exchange/Metamorph/LgdYhn",
				"https://www.pathofexile.com/trade/exchange/Metamorph/Yo07hY");
		currencies.add(jew);
		//alchemy
		alch = new Currency("alch", "https://www.pathofexile.com/trade/exchange/Metamorph/rPe7CQ",
				"https://www.pathofexile.com/trade/exchange/Metamorph/Rwoqi7");
		currencies.add(alch);
		//gcp
		gcp = new Currency("gcp", "https://www.pathofexile.com/trade/exchange/Metamorph/6oJbTG",
				"https://www.pathofexile.com/trade/exchange/Metamorph/B22wf8");
		currencies.add(gcp);
		//chromatic
		chrom = new Currency("chrom", "https://www.pathofexile.com/trade/exchange/Metamorph/eLabsL",
				"https://www.pathofexile.com/trade/exchange/Metamorph/qWRPSg");
		currencies.add(chrom);
		//chance
		chance = new Currency("chance", "https://www.pathofexile.com/trade/exchange/Metamorph/D6mLdS5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/bYoGiL");
		currencies.add(chance);
		//chisel
		chisel = new Currency("chisel", "https://www.pathofexile.com/trade/exchange/Metamorph/EL9yT5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/l3wjiV");
		currencies.add(chisel);
		//scouring
		scour = new Currency("scour", "https://www.pathofexile.com/trade/exchange/Metamorph/3D7GU5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/b5VKIL");
		currencies.add(scour);
		//divine
		divine = new Currency("divine", "https://www.pathofexile.com/trade/exchange/Metamorph/Eov4F5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/19pKtK");
		currencies.add(divine);
		//vaal
		vaal = new Currency("vaal", "https://www.pathofexile.com/trade/exchange/Metamorph/54XoTa",
				"https://www.pathofexile.com/trade/exchange/Metamorph/poMXU0");
		currencies.add(vaal);
		//regret
		regret = new Currency("regret", "https://www.pathofexile.com/trade/exchange/Metamorph/q2qvCg",
				"https://www.pathofexile.com/trade/exchange/Metamorph/L2yGun");
		currencies.add(regret);
		//glassblower
		ba = new Currency("ba", "https://www.pathofexile.com/trade/exchange/Metamorph/O73rCE",
				"https://www.pathofexile.com/trade/exchange/Metamorph/X3l9RsP");
		currencies.add(ba);
		//silver coin
		silver = new Currency("silver", "https://www.pathofexile.com/trade/exchange/Metamorph/p6XXt0",
				"https://www.pathofexile.com/trade/exchange/Metamorph/q2YPCg");
		currencies.add(silver);
		//exalted
		exa = new Currency("exa", "https://www.pathofexile.com/trade/exchange/Metamorph/zavrGvU4",
				"https://www.pathofexile.com/trade/exchange/Metamorph/b8vDrLHL");
		currencies.add(exa);
	}
}