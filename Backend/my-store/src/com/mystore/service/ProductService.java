package com.mystore.service;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;
import com.mystore.dto.*;
import org.json.JSONArray;

public interface ProductService {


	public List<ProductDetails> getProducts(ProductFilter filter) throws Exception;
}
