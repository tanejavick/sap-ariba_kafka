package com.examples.java.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

//Partitioner logic
public class CustomPartitioner implements Partitioner {
	private static final int PARTITION_COUNT = 3;
	
	@Override
	public void configure(Map<String, ?> configs) {
		System.out.println("Partitioner specific config..");
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
			Cluster cluster) {
		Integer keyInt = Integer.parseInt(key.toString());
		int partition = keyInt % PARTITION_COUNT;
		System.out.println("Sending message to partition - " + partition);
		return partition;
	}

	@Override
	public void close() {
		System.out.println("Cleanup..");
	}
}