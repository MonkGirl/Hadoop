package com.monkgirl.hadoopstudy;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class PutMerge {
	public static void main(String[] args){
		Configuration conf = new Configuration();
		try {
			FileSystem hdfs = FileSystem.get(conf);
			//FileSystem local = FileSystem.getLocal(conf);
			
			String inputPath = "hdfs://192.168.1.2:9000/home/hadoop/user/input/file1.txt";
		    String outputPath = "hdfs://192.168.1.2:9000/home/hadoop/user/output";
			
			Path inputHdfsFiles = new Path(inputPath);
			Path outputHdfsFiles = new Path(outputPath);
			
			//FileStatus[] inputFiles = local.listStatus(inputDir);
			//FileStatus[] inputFiles = hdfs.
			FSDataOutputStream out = hdfs.create(outputHdfsFiles);
			//FSDataInputStream in = hdfs.open(inputHdfsFiles);
			
			//for(int i=0;i<inputFiles.length;i++) {
				//System.out.println(inputFiles[i].getPath().getName());
				//FSDataInputStream in = local.open(inputFiles[i].getPath());
			    FSDataInputStream in = hdfs.open(inputHdfsFiles);
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				while((bytesRead = in.read(buffer))>0) {
					out.write(buffer, 0, bytesRead);
				}
				in.close();
			//}
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
