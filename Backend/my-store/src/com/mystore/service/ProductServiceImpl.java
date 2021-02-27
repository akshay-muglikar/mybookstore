package com.mystore.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.dto.ProductDetails;
import com.mystore.dto.ProductFilter;
import com.mystore.helper.ProductHelper;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.JsonPath;
import com.mystore.constants.Constants;
import com.mystore.dao.*;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDetailsDao productDetailsDao;

	@Autowired
	FilterService filterService;

	@Override
	public List<ProductDetails> getProducts(ProductFilter filter) throws Exception {
		InputStream is = getProducts();
		try {
			List<ProductDetails> data = readJsonStream(is, filter);
			return data;
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}

	}

	private InputStream getProducts() throws Exception {

		InputStream is = productDetailsDao.getProducts();
		if (is == null) {
			ProductHelper.fetchProducts();
			return productDetailsDao.getProducts();
		}
		return is;
	}

	private List<ProductDetails> readJsonStream(InputStream in, ProductFilter pf) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		List<ProductDetails> books = new ArrayList<ProductDetails>();
		Gson g = new Gson();

		reader.beginArray();

		int item = 0;
		int offset = getOffset(pf.getPage());
		while (reader.hasNext() && item < Constants.PAGE_SIZE) {
			ProductDetails book = g.fromJson(reader, ProductDetails.class);
			if (offset != 0 && !pf.getIsFilterApplied()) {
				offset--;
				continue;
			}
		

			if (!pf.getIsFilterApplied()) {
				item++;
			}
			books.add(book);

		}
		if (item < Constants.PAGE_SIZE) {
			reader.endArray();
		}

		reader.close();
		if (pf.getIsFilterApplied()) {
			return filterService.filterData(books, pf, offset, Constants.PAGE_SIZE);
		}
		return books;
	}

	private int getOffset(int page) {

		return (page - 1) * Constants.PAGE_SIZE;
	}

}
