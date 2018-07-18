package com.monkgirl.hadoopstudy;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Edge implements WritableComparable<Edge>{
	private String departureNode;
	
	private String arrivalNode;

	public String getDepartureNode() {
		return departureNode;
	}

	public void setDepartureNode(String departureNode) {
		this.departureNode = departureNode;
	}

	public String getArrivalNode() {
		return arrivalNode;
	}

	public void setArrivalNode(String arrivalNode) {
		this.arrivalNode = arrivalNode;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.departureNode = in.readUTF();
		this.arrivalNode = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.departureNode);
		out.writeUTF(this.arrivalNode);
	}

	@Override
	public int compareTo(Edge o) {
		return (this.departureNode.compareTo(o.getDepartureNode())!=0?
				this.departureNode.compareTo(o.getDepartureNode()):
					this.arrivalNode.compareTo(o.getArrivalNode()));
	}

}
