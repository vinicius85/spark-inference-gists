package br.com.tvinicius.spark;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.stereotype.Component;

import kafka.serializer.StringDecoder;

@Component
public class KafkaConsumer implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		SparkConf sparkConf = new SparkConf().setAppName("Crossover App").setMaster("local[2]");
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(10));

		HashSet<String> topicsSet = new HashSet<String>(Arrays.asList("streaming_topic"));
		HashMap<String, String> kafkaParams = new HashMap<String, String>();
		kafkaParams.put("metadata.broker.list", "localhost:9092");

		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jssc, String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);

		messages
				.map(item -> item._2)
				
				.foreachRDD(rdd -> {
					rdd.foreachPartition(partition -> 
						partition.forEachRemaining(dataPoint -> System.out.println(dataPoint)));
				});

		jssc.start();
		jssc.awaitTermination();

	}

}
