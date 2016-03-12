package br.com.tvinicius.spark;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.stereotype.Component;

import br.com.tvinicius.spark.jobs.TopQueriesTask;
import br.com.tvinicius.spark.model.DataPoint;
import br.com.tvinicius.spark.util.GsonUtils;
import kafka.serializer.StringDecoder;

@Component
public class KafkaConsumer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
		
		SparkConf sparkConf = new SparkConf().setAppName("Spark App").setMaster("local[2]");
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(10));

		HashSet<String> topicsSet = new HashSet<String>(Arrays.asList("streaming_topic"));
		HashMap<String, String> kafkaParams = new HashMap<String, String>();
		kafkaParams.put("metadata.broker.list", "localhost:9092");

		JavaDStream<DataPoint> stream = KafkaUtils
				.createDirectStream(jssc, String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet)
				.map(item -> GsonUtils.fromJson(item._2(), DataPoint.class))
				.cache();
		
		new TopQueriesTask().process(stream);

		jssc.start();
		jssc.awaitTermination();

	}

}
