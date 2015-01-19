package mapReduce;

import java.util.ArrayList;
import java.util.Collections;

public class RecommendedPerson implements Comparable<RecommendedPerson> {
	
	private String personID;
	private int numMutualFriends;
	
	public RecommendedPerson(String personID, int numMutualFriends)
	{
		this.personID = personID;
		this.numMutualFriends = numMutualFriends;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public int getNumMutualFriends() {
		return numMutualFriends;
	}

	public void setNumMutualFriends(int numMutualFriends) {
		this.numMutualFriends = numMutualFriends;
	}
	
	@Override
	public String toString()
	{
		return personID + " " + numMutualFriends;
	}

	@Override
	public int compareTo(RecommendedPerson p) {
		int temp = this.numMutualFriends - p.numMutualFriends;
		return temp * 100000 + Integer.parseInt(p.personID)-Integer.parseInt(this.personID);
		
	}
	
	public static void main(String [] args)
	{
		ArrayList<RecommendedPerson> al = new ArrayList<RecommendedPerson>();
		al.add(new RecommendedPerson("123", 1));
		al.add(new RecommendedPerson("456", 1));
		al.add(new RecommendedPerson("001", 2));
		al.add(new RecommendedPerson("200", 2));
		Collections.sort(al);
		for (RecommendedPerson p:al)
		{
			System.out.println(p);
		}
			
	}

}
