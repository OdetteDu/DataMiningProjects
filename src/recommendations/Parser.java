package recommendations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Parser {

	public static int SUPPORT = 100;
	private String filePath;
	private HashMap<String, Integer> itemCount;
	private HashMap<String, Integer> frequentItemCount;
	private HashMap<String, Integer> itemPairCount;
	private HashMap<String, Integer> frequentItemPairCount;
	private HashMap<String, Integer> itemTripleCount;
	private HashMap<String, Integer> frequentItemTripleCount;

	public Parser(String filePath)
	{
		this.filePath = filePath;
		this.itemCount = new HashMap<String, Integer>();
		this.itemPairCount = new HashMap<String, Integer>();
		this.itemTripleCount = new HashMap<String, Integer>();
	}
	
	public void printHashMap(HashMap<String, Integer> map)
	{
		for (String key: map.keySet())
		{
			System.out.println(key+" "+map.get(key));
		}
	}

	public void firstParse()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			while (line != null)
			{
				HashSet<String> basket = new HashSet<String>(Arrays.asList(line.split(" ")));
				/*
				 * insert code to do with the basket in itemSet
				 */
				countItems(basket);
				/*
				 * end
				 */
				line = br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void countItems(HashSet<String> basket)
	{
		for (String s : basket)
		{
			int temp = this.itemCount.get(s) == null?0:this.itemCount.get(s);
			this.itemCount.put(s, temp+1);
		}
	}
	
	public HashMap<String, Integer> getFrequentItemCount()
	{
		HashMap<String, Integer> frequentItem = new HashMap<String, Integer>();
		for (String key: this.itemCount.keySet())
		{
			int count = this.itemCount.get(key);
			if(count >= 100)
			{
				frequentItem.put(key, count);
			}
		}
		this.frequentItemCount = frequentItem;
		return frequentItem;
	}
	
	public void secondParse()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			while (line != null)
			{
				HashSet<String> basket = new HashSet<String>(Arrays.asList(line.split(" ")));
				/*
				 * insert code to do with the basket in itemSet
				 */
				ArrayList<String> frequentItemInBasket = getFrequentItemInBasket(basket);
				countItemPairs(frequentItemInBasket);
				/*
				 * end
				 */
				line = br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getFrequentItemInBasket(HashSet<String> basket)
	{
		Set<String> frequentItems = this.frequentItemCount.keySet();
		ArrayList<String> frequentItemInBasket = new ArrayList<String>();
		for (String s:basket)
		{
			if(frequentItems.contains(s))
			{
				frequentItemInBasket.add(s);
			}
		}
		return frequentItemInBasket;
	}
	
	public void countItemPairs(ArrayList<String> frequentItemInBasket)
	{
		for(int i=0; i<frequentItemInBasket.size(); i++)
		{
			String item1 = frequentItemInBasket.get(i);
			for(int j=i+1; j<frequentItemInBasket.size(); j++)
			{
				String item2 = frequentItemInBasket.get(j);
				String key = item1.compareTo(item2)<0?item1+" "+item2:item2+" "+item1;
				int count = this.itemPairCount.get(key) == null?0:this.itemPairCount.get(key);
				this.itemPairCount.put(key, count+1);
			}
		}
	}
	
	public HashMap<String, Integer> getFrequentItemPairCount()
	{
		this.frequentItemPairCount = new HashMap<String, Integer>();
		for (String key: this.itemPairCount.keySet())
		{
			int count = this.itemPairCount.get(key);
			if(count >= 100)
			{
				this.frequentItemPairCount.put(key, count);
			}
		}
		return this.frequentItemPairCount;
	}
	
	public void thirdParse()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			while (line != null)
			{
				HashSet<String> basket = new HashSet<String>(Arrays.asList(line.split(" ")));
				/*
				 * insert code to do with the basket in itemSet
				 */
				ArrayList<String> frequentItemInBasket = getFrequentItemInBasket(basket);
				ArrayList<String> frequentItemPairInBasket = getFrequentItemPairInBasket(frequentItemInBasket);
				countItemTriples(frequentItemInBasket, frequentItemPairInBasket);
				/*
				 * end
				 */
				line = br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private ArrayList<String> getFrequentItemPairInBasket(ArrayList<String> basket) {
		
		ArrayList<String> frequentItemPairInBasket = new ArrayList<String>();
		for(int i=0; i<basket.size(); i++)
		{
			String item1 = basket.get(i);
			for(int j=i+1; j<basket.size(); j++)
			{
				String item2 = basket.get(j);
				String key = item1.compareTo(item2)<0?item1+" "+item2:item2+" "+item1;
				if(this.frequentItemPairCount.containsKey(key))
				{
					frequentItemPairInBasket.add(key);
				}
			}
		}
		return frequentItemPairInBasket;
	}
	
	public void countItemTriples(ArrayList<String> frequentItemInBasket, ArrayList<String> frequentItemPairInBasket)
	{
		HashSet<String> triplets = new HashSet<String>();
		for(String pair : frequentItemPairInBasket)
		{
			for(String single : frequentItemInBasket)
			{
				ArrayList<String> triples = new ArrayList<String>();
				HashSet<String> tripleSets = new HashSet<String>();
				triples.addAll(Arrays.asList(pair.split(" ")));
				tripleSets.addAll(Arrays.asList(pair.split(" ")));
				triples.add(single);
				tripleSets.add(single);
				if(tripleSets.size()<3)
				{
					break;
				}
				Collections.sort(triples);
				String key = "";
				for(String s : triples)
				{
					key+=s+" ";
				}
				key = key.trim();
				triplets.add(key);
			}
		}
		
		for(String key : triplets)
		{
			int value = this.itemTripleCount.get(key)==null?0:this.itemTripleCount.get(key);
			this.itemTripleCount.put(key, value+1);
		}
	}
	
	public HashMap<String, Integer> getFrequentItemTripleCount()
	{
		this.frequentItemTripleCount = new HashMap<String, Integer>();
		for (String key: this.itemTripleCount.keySet())
		{
			int count = this.itemTripleCount.get(key);
			if(count >= 100)
			{
				this.frequentItemTripleCount.put(key, count);
			}
		}
		return this.frequentItemTripleCount;
	}

	public static void main(String[] args) {
		System.out.println(args[0]);
		Parser p = new Parser(args[0]);
		p.firstParse();
		HashMap<String, Integer> frequentItem =p.getFrequentItemCount();
		p.secondParse();
		HashMap<String, Integer> frequentItemPair = p.getFrequentItemPairCount();
		p.thirdParse();
		HashMap<String, Integer> frequentItemTriple = p.getFrequentItemTripleCount();
		// finish get pair and triple
		ConfidenceCalculator cc = new ConfidenceCalculator(frequentItem, frequentItemPair, frequentItemTriple);
		ArrayList<Pair> pairs = cc.calculatePair();
		for ( int i = pairs.size()-1; i>=pairs.size()-5; i--)
		{
			System.out.println(pairs.get(i));
		}
		ArrayList<Triple> triples = cc.calculateTriple();
		for ( int i = triples.size()-1; i>= triples.size()-5; i--)
		{
			System.out.println(triples.get(i));
		}
//		p.printHashMap(frequentItem);
//		p.printHashMap(frequentItemPair);
//		System.out.println(frequentItem.size());
//		System.out.println(frequentItemPair.size());
//		System.out.println(frequentItemTriple.size());
		
	}

}
