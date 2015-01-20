package recommendations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ConfidenceCalculator {
	
	private HashMap<String, Integer> singles;
	private HashMap<String, Integer> pairs;
	private HashMap<String, Integer> triples;
	
	public ConfidenceCalculator(HashMap<String, Integer> singles, HashMap<String, Integer> pairs, HashMap<String, Integer> triples)
	{
		this.singles = singles;
		this.pairs = pairs;
		this.triples = triples;
	}
	
	public ArrayList<Pair> calculatePair()
	{
		ArrayList<Pair> p = new ArrayList<Pair>();
		for (String s : pairs.keySet())
		{
			String [] temp = s.split(" ");
			String a = temp[0];
			String b = temp[1];
			int x = singles.get(a);
			int y = singles.get(b);
			int z = pairs.get(s);
//			if(a.equals("FRO40251")||b.equals("FRO40251"))
//			System.out.println(a+": "+x+" "+b+": "+y+" "+z+" "+(double)z/x+" "+(double)z/y);
			Pair p1 = new Pair(a, b, (double)z/x);
			Pair p2 = new Pair(b, a, (double)z/y);
			p.add(p1);
			p.add(p2);
		}
		Collections.sort(p);
		return p;
	}
	
	public ArrayList<Triple> calculateTriple()
	{
		ArrayList<Triple> t = new ArrayList<Triple>();
		for (String s : triples.keySet())
		{
			String[] temp = s.split(" ");
			Arrays.sort(temp);
			String a = temp[0];
			String b = temp[1];
			String c = temp[2];
			Triple t1 = new Triple(a, b, c, (double)triples.get(s)/pairs.get(a+" "+b));
			Triple t2 = new Triple(a, c, b, (double)triples.get(s)/pairs.get(a+" "+c));
			Triple t3 = new Triple(b, c, a, (double)triples.get(s)/pairs.get(b+" "+c));
			t.add(t1);
			t.add(t2);
			t.add(t3);
		}
		Collections.sort(t);
		return t;
	}

}
