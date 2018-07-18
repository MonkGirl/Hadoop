package com.monkgirl.hadoopInAction;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueLineRecordReader;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class TimeUrlTextInputFormat extends FileInputFormat<Text, URLWritable>{

	@Override
	public RecordReader<Text, URLWritable> getRecordReader(InputSplit input, JobConf job, Reporter reporter)
			throws IOException {
		return new TimeUrlLineRecordReader(job, (FileSplit)input);
	}
}

class TimeUrlLineRecordReader implements RecordReader<Text, URLWritable>{
	
	private KeyValueLineRecordReader lineReader;
	
	private Text lineKey;
	
	private Text lineValue;
	
	public TimeUrlLineRecordReader(JobConf job, FileSplit split) throws IOException {
		lineReader = new KeyValueLineRecordReader(job, split);
		lineKey = lineReader.createKey();
		lineValue = lineReader.createValue();
	}

	@Override
	public void close() throws IOException {
		lineReader.close();
	}

	@Override
	public Text createKey() {
		return new Text("");
	}

	@Override
	public URLWritable createValue() {
		return new URLWritable();
	}

	@Override
	public long getPos() throws IOException {
		return lineReader.getPos();
	}

	@Override
	public float getProgress() throws IOException {
		return lineReader.getProgress();
	}

	@Override
	public boolean next(Text key, URLWritable value) throws IOException {
		if(!lineReader.next(lineKey, lineValue)) {
			return false;
		}
		key.set(lineKey);
		value.set(lineValue.toString());
		return true;
	}
	
}
