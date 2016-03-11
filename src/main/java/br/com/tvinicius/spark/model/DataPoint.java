package br.com.tvinicius.spark.model;

import java.io.Serializable;

public class DataPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;

	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
