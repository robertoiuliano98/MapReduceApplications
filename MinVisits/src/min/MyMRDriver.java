package min;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyMRDriver {

	public static void main(String[] args) throws Exception {
		
		Job j = Job.getInstance();
		j.setJarByClass(min.MyMRDriver.class);
		j.setJobName("MinVisits");
		j.setNumReduceTasks(7);
		
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		
		j.setMapperClass(min.MyMap.class);
		j.setReducerClass(min.MyRed.class);
	
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		
		System.exit(j.waitForCompletion(true) ? 0:1);

	}

}
