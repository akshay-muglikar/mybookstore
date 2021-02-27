package com.mystore.dao;

import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;

import com.mystore.dto.ProductDetails;

public interface ProductDetailsDao {
	public InputStream getProducts();
}
