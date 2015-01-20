package recommendations;

public class Pair implements Comparable<Pair> {
	
	private String a;
	private String b;
	private double confidence;
	
	public Pair(String a, String b, double confidence)
	{
		this.a = a;
		this.b = b;
		this.confidence = confidence;
	}
	
	public String toString()
	{
		return a+" -> "+b+" : "+confidence;
	}

	@Override
	public int compareTo(Pair o) {
		if(this.confidence == o.confidence)
		{
			return 0;
		}
		else if(this.confidence < o.confidence)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

}
