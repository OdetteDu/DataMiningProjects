package mapReduce;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NumMutualFriendsMap extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String[] friendships = value.toString().split("\t");
		String user = friendships[0];
		if(friendships.length > 1)
		{
			String[] friends = friendships[1].split(",");
			//System.out.println("User: "+user);
			//System.out.println("Friends: ");
			for (int i=0; i<friends.length; i++)
			{
				String temp = user+" "+friends[i];
				context.write(new Text(temp), new Text("-1"));
				//System.out.println(temp+" -1");
				for (int j=0; j<friends.length; j++)
				{
					if(i != j)
					{
						temp = friends[i]+" "+friends[j];
						context.write(new Text(temp), new Text(user));
						//System.out.println(temp+" "+user);
					}
				}
			}
		}

	}
}
