package mapReduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RecommendFriendsReduce extends Reducer<IntWritable, Text, IntWritable, Text> {

	  @Override
	  public void reduce(IntWritable key, Iterable<Text> values, Context context)
	      throws IOException, InterruptedException {
		  //System.out.println("Person: "+key+"'s recommendation list:");
		  ArrayList<RecommendedPerson> recommendations = new ArrayList<RecommendedPerson>();
		  for (Text t: values)
		  {
			  String [] temp = t.toString().split(" ");
			  int numMutualFriends = Integer.parseInt(temp[0]);
			  String recommended = temp[1];
			  //System.out.println("Recommend "+recommended+" because of "+numMutualFriends);
			  RecommendedPerson rp = new RecommendedPerson(recommended, numMutualFriends);
			  recommendations.add(rp);
		  }
		  Collections.sort(recommendations);
		  String result = "Recommendations for user "+key+": \n";
		  int end = recommendations.size() > 20?recommendations.size()-20:0;
		  for (int i=recommendations.size()-1; i>=end; i-=2)
		  {
			  //System.out.println(recommendations.get(i));
			  result += recommendations.get(i)+"\n";
		  }
		  context.write(key, new Text(result));
	  }  
	}