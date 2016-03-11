package br.com.tvinicius.spark.jobs;

import java.io.Serializable;

import org.apache.spark.streaming.api.java.JavaDStream;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.google.inject.Inject;

import br.com.tvinicius.spark.model.DataPoint;
import br.com.tvinicius.spark.repository.AnalyticsRepository;

@Component
public class TopQueriesTask implements StreamingTask, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ObjectMapper mapper;
	
	@Inject
	private AnalyticsRepository repository;
	
	@Override
	public void process(JavaDStream<DataPoint> data) {
		data
			.map(item -> item.getContent())
			.foreachRDD(System.out::println);
	}

}
