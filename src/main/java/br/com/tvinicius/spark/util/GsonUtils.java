package br.com.tvinicius.spark.util;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Gson gson = new GsonBuilder().create();
	
	public static <T> T fromJson(String item, Class<T> clazz){
		return gson.fromJson(item, clazz);
	}

}
