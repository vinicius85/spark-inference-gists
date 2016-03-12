package br.com.tvinicius.spark.repository;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import br.com.tvinicius.spark.model.DataPoint;
import br.com.tvinicius.spark.model.TopQuery;

@Repository
public class AnalyticsRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String INSERT_DATAPOINT = "INSERT INTO datapoint (type,content) VALUES (?,?)";

	public void insertTopQuery(Iterator<DataPoint> dataPoints) {
		try {
			Cluster cluster = Cluster.builder().addContactPoints(InetAddress.getLocalHost()).build();

			Session session = cluster.connect("mykeyspace");

			PreparedStatement statement = session.prepare(INSERT_DATAPOINT);

			dataPoints.forEachRemaining(dataPoint -> {
				BoundStatement boundStatement = statement.bind(dataPoint.getType(), dataPoint.getContent());
				session.execute(boundStatement);
			});

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
