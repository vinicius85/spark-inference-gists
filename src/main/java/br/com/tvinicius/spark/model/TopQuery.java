package br.com.tvinicius.spark.model;

import java.io.Serializable;

public class TopQuery implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private final String keywords;

	private final String userId;

	public TopQuery(String keywords, String userId) {
		super();
		this.keywords = keywords;
		this.userId = userId;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getUserId() {
		return userId;
	}

}
