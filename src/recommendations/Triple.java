package recommendations;

public class Triple implements Comparable<Triple> {
	
	private String a;
	private String b;
	private String c;
	private double confidence;
	
	public Triple(String a, String b, String c, double confidence)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.confidence = confidence;
	}
	
	public String toString()
	{
		return a+","+b+" -> "+c+" : "+confidence;
	}

	@Override
	public int compareTo(Triple o) {
		if(this.confidence == o.confidence)
		{
			if(this.a.compareTo(o.a)>0)
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		else if(this.confidence < o.confidence)
		{
			return -10;
		}
		else
		{
			return 10;
		}
	}

}
