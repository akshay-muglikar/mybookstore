package com.mystore.helper;


import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mystore.constants.Constants;
import com.mystore.dto.ProductDetails;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

public class ProductHelper{
		
	public static void fetchProducts() throws Exception{
		try {
			
			File f = new File("C://test.txt");
			FileUtils.writeStringToFile(f, "", "UTF-8");
			f.createNewFile();
			URL url = new URL(Constants.BOOKS_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(Integer.MAX_VALUE);
			
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			 JsonReader reader = new JsonReader(in);
			 Gson g = new Gson();
			 reader.beginArray();
			 JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	        writer.setIndent("  ");
	        writer.beginArray();
		        
			while(reader.hasNext()) {
			ProductDetails message =  g.fromJson(reader, ProductDetails.class);
			//FileUtils.writeStringToFile(f, data.getAsString(), "UTF-8", true);
			g.toJson(message, ProductDetails.class, writer);  
			}
			writer.endArray();
	        writer.close();
	        reader.close();
			
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
				
	}
	
}
