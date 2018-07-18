package com.monkgirl.hadoopstudy;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class EdgePartitioner implements Partitioner<Edge, Writable>{

	@Override
	public void configure(JobConf conf) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public int getPartition(Edge key, Writable value, int numPartitions) {
		return key.getDepartureNode().hashCode()%numPartitions;
	}

}
