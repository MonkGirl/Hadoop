package com.monkgirl.hadoopstudy;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordCount3 {
	enum Counter{
		LINESKIP;
	}
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text>{
		public void map(LongWritable key, Text value, Context context) throws IOException{
			String line = value.toString();
			try {
				String[] lineSplit = line.split(" ");
				String anum = lineSplit[0];
				String bnum = lineSplit[1];
			}catch(ArrayIndexOutOfBoundsException e) {
				context.getCounter(Counter.LINESKIP).increment(1);
				return;
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
			String valueString;
			String out = "";
			for(Text value : values) {
				valueString = value.toString();
				out += valueString +"|";
			}
			
			context.write(key, new Text(out));
		}
	}
	
	public static void main(String...args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = new Job(conf, "WordCount3");
		job.setJarByClass(WordCount3.class);
		
		String path = "hdfs://192.168.1.9:9000/home/hadoop/user/output";
		Path outPath = new Path(path);
		outPath.getFileSystem(conf).delete(outPath, true);
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.1.9:9000/home/hadoop/user/input"));
		FileOutputFormat.setOutputPath(job, outPath);
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reducer.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.waitForCompletion(true);
		System.exit(job.isSuccessful()?0:1);
	}
}
