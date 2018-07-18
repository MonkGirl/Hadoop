package com.monkgirl.hadoopInAction;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.io.Writable;

public class URLWritable implements Writable{
	
	protected URL url;
	
	public URLWritable() {
		
	}
	
	public URLWritable(URL url) {
		this.url = url;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		url = new URL(input.readUTF());
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeUTF(url.toString());
	}
	
	public void set(String urlStr) throws MalformedURLException {
		url = new URL(urlStr);
	}

}
