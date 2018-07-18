package com.monkgirl.hadoopstudy;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.LongSumReducer;
import org.apache.hadoop.mapred.lib.TokenCountMapper;


public class WordCount2 {
    public static void main(String[] args) {
    	JobClient client = new JobClient();
    	JobConf conf = new JobConf(WordCount2.class);
    	
    	String rootDir = System.getProperty("user.dir");
		String inputPath = rootDir + File.separator + "input";
		String outputPath = rootDir + File.separator + "output";
    	
    	FileInputFormat.setInputPaths(conf, new Path(inputPath));
    	FileOutputFormat.setOutputPath(conf, new Path(outputPath));
    	
    	conf.setOutputKeyClass(Text.class);
    	conf.setOutputValueClass(LongWritable.class);
    	conf.setMapperClass(TokenCountMapper.class);
    	conf.setCombinerClass(LongSumReducer.class);
    	conf.setReducerClass(LongSumReducer.class);
    	
    	client.setConf(conf);
    	try {
			JobClient.runJob(conf);
			if(client!=null) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
