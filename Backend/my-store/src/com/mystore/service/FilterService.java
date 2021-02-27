package com.mystore.service;

import java.util.List;

import com.mystore.dto.ProductDetails;
import com.mystore.dto.ProductFilter;

public interface FilterService {
	
	public boolean validate(ProductDetails pr, ProductFilter pf);
	
	public List<ProductDetails> search(List<ProductDetails> listData, String name);

	public List<ProductDetails> filterData(List<ProductDetails> books, ProductFilter pf, int offset, int pageSize);

}
