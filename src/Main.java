import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	private static ArrayList<Currency> currencies = new ArrayList<Currency>(), profitableCurrencies = new ArrayList<Currency>();
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//initiate currency
		initiateCurrency();

		//initiate website
		WebsiteHandler handle = new WebsiteHandler(Defines.POESESSID);

		//main
		print("======================running..");
		for (int i = 0; i < currencies.size(); i++) {
			handle.parse(currencies.get(i));
			currencies.get(i).calcBuyPrice();
			currencies.get(i).calcSellPrice();
			currencies.get(i).calcProfit();
			System.out.println(currencies.get(i));
		}
		
		//sort by descending profit then sell those with profit ratio > 40%
		Collections.sort(currencies, Collections.reverseOrder());
		for(Currency c: currencies) {
			System.out.print("Selected profitable currencies: ");
			if (c.getProfitRatio() > Defines.LOWEST_ALLOWED_PROFIT_RATIO) {
				profitableCurrencies.add(c);
				System.out.print(c.getName() + ", ");
			}
		}
		
		String forumPost = handle.updateShopThread(profitableCurrencies, profitableCurrencies.size());
		System.out.println("\n" + forumPost);
		
		//cleanup
		handle.exit();
		print("======================done..");
	}

	public static void print(String string) {
		System.out.println(string);
	}
	
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
				"https://www.pathofexile.com/trade/exchange/Metamorph/Ny7gfR");
		currencies.add(alt);
		//fusing
		fuse = new Currency("fuse", "https://www.pathofexile.com/trade/exchange/Metamorph/jW58VLUX",
				"https://www.pathofexile.com/trade/exchange/Metamorph/V5VkVdSp");
		currencies.add(fuse);
		//jewellers
		jew = new Currency("jew", "https://www.pathofexile.com/trade/exchange/Metamorph/LgdYhn",
				"https://www.pathofexile.com/trade/exchange/Metamorph/1EpVu5");
		currencies.add(jew);
		//alchemy
		alch = new Currency("alch", "https://www.pathofexile.com/trade/exchange/Metamorph/rPe7CQ",
				"https://www.pathofexile.com/trade/exchange/Metamorph/yYYOiR");
		currencies.add(alch);
		//gcp
		gcp = new Currency("gcp", "https://www.pathofexile.com/trade/exchange/Metamorph/18jvcV",
				"https://www.pathofexile.com/trade/exchange/Metamorph/ADa4f5");
		currencies.add(gcp);
		//chromatic
		chrom = new Currency("chrom", "https://www.pathofexile.com/trade/exchange/Metamorph/AePWsL",
				"https://www.pathofexile.com/trade/exchange/Metamorph/AMKqTJ");
		currencies.add(chrom);
		//chance
		chance = new Currency("chance", "https://www.pathofexile.com/trade/exchange/Metamorph/0YR8Ig",
				"https://www.pathofexile.com/trade/exchange/Metamorph/X3JBsP");
		currencies.add(chance);
		//chisel
		chisel = new Currency("chisel", "https://www.pathofexile.com/trade/exchange/Metamorph/4my8I9",
				"https://www.pathofexile.com/trade/exchange/Metamorph/zbVRF4");
		currencies.add(chisel);
		//scouring
		scour = new Currency("scour", "https://www.pathofexile.com/trade/exchange/Metamorph/EBEzt5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/rbdMHQ");
		currencies.add(scour);
		//divine
		divine = new Currency("divine", "https://www.pathofexile.com/trade/exchange/Metamorph/NpeJc0",
				"https://www.pathofexile.com/trade/exchange/Metamorph/9z28fK", 3, 1);
		currencies.add(divine);
		//vaal
		vaal = new Currency("vaal", "https://www.pathofexile.com/trade/exchange/Metamorph/EB9LC5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/18GVuV");
		currencies.add(vaal);
		//regret
		regret = new Currency("regret", "https://www.pathofexile.com/trade/exchange/Metamorph/9z6ztK",
				"https://www.pathofexile.com/trade/exchange/Metamorph/zbJai4");
		currencies.add(regret);
		//glassblower
		ba = new Currency("ba", "https://www.pathofexile.com/trade/exchange/Metamorph/3q5Jc5",
				"https://www.pathofexile.com/trade/exchange/Metamorph/v089CE");
		currencies.add(ba);
		//silver coin
		silver = new Currency("silver", "https://www.pathofexile.com/trade/exchange/Metamorph/XL5BFP",
				"https://www.pathofexile.com/trade/exchange/Metamorph/6zEkUG");
		currencies.add(silver);
		//exalted
		exa = new Currency("exa", "https://www.pathofexile.com/trade/exchange/Metamorph/glRewPiQ",
				"https://www.pathofexile.com/trade/exchange/Metamorph/V59BW2Ip", 3, 3);
		currencies.add(exa);
		
		for (int i = 0; i < currencies.size(); i++) {
			currencies.get(i).setBuyX(i / 12);
			currencies.get(i).setBuyY(i - (i / 12) * 12);
			currencies.get(i).setSellX((i / 12) + 2);
			currencies.get(i).setSellY(i - (i / 12) * 12);
		}
	}
}