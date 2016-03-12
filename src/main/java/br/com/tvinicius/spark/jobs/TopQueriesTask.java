package br.com.tvinicius.spark.jobs;

import java.io.Serializable;

import org.apache.spark.streaming.api.java.JavaDStream;

import br.com.tvinicius.spark.model.DataPoint;
import br.com.tvinicius.spark.repository.AnalyticsRepository;

public class TopQueriesTask implements StreamingTask, Serializable {
	
	private static final long serialVersionUID = 1L;

	private AnalyticsRepository repository;
	
	public TopQueriesTask() {
		this.repository = new AnalyticsRepository();
	}
	
	@Override
	public void process(JavaDStream<DataPoint> data) {
		data
			.foreachRDD(rdd -> {
				rdd.foreachPartition(repository::insertTopQuery);
			});
	}

}
