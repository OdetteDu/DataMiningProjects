package mapReduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RecommendFriendsMap extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//System.out.println("Number of Mutual Friends: "+value);
		String [] temp = value.toString().split("\t");
		int numMutualFriends = Integer.parseInt(temp[1]);
		temp = temp[0].split(" ");
		int person1 = Integer.parseInt(temp[0]);
		int person2 = Integer.parseInt(temp[1]);
		String value1 = numMutualFriends+" "+person2;
		String value2 = numMutualFriends+" "+person1;
		context.write(new IntWritable(person1), new Text(value1));
		context.write(new IntWritable(person2), new Text(value2));
	}
}
