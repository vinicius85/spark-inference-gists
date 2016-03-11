package br.com.tvinicius.spark.repository;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import br.com.tvinicius.spark.model.TopQuery;

@Repository
public class AnalyticsRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	public void insertTopQuery(Iterator<TopQuery> topQueries) {
		try {
			Cluster cluster = Cluster.builder().addContactPoints(InetAddress.getLocalHost()).build();

			Session session = cluster.connect("mykeyspace");
			
			session.execute("");


		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
