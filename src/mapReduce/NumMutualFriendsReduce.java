package mapReduce;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class NumMutualFriendsReduce extends Reducer<Text, Text, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    //System.out.println("Key: "+key+"\nValues:");
    int numMutualFriends = 0;
    for (Text v : values)
    {
    	int id = Integer.parseInt(v.toString());
    	if(id < 0)
    	{
    		//System.out.println(key+" are already friends. No recommendation required. ");
    		return;
    	}
    	numMutualFriends ++;
    }
    context.write(new Text(key), new IntWritable(numMutualFriends));
  }
}