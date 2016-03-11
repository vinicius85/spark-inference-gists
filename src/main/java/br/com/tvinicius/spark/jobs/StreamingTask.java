package br.com.tvinicius.spark.jobs;

import org.apache.spark.streaming.api.java.JavaDStream;

import br.com.tvinicius.spark.model.DataPoint;

public interface StreamingTask {
	
	void process(JavaDStream<DataPoint> data);

}
