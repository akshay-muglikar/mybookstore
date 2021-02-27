package com.mystore.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.StringUtils;

public class ProductFilter {

	private int page;
	private String sortBy;
	private String name;
	private Long avgRating;
	private Long minPrice;
	private Long maxPrice;
	
	private boolean isFilterApplied;
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(long avgRating) {
		this.avgRating = avgRating;
	}
	public Long getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(long minPrice) {
		this.minPrice = minPrice;
	}
	public Long getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(long maxPrice) {
		this.maxPrice = maxPrice;
	}
	public void setFilterApplied(boolean isFilterApplied) {
		this.isFilterApplied = isFilterApplied;
	}
	public boolean getIsFilterApplied() {
		return isFilterApplied;
	}
	public void setIsFilterApplied(boolean isFilterApplied) {
		this.isFilterApplied = isFilterApplied;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public ProductFilter(int page, String sortBy, String name, Long avgRating, Long minPrice, Long maxPrice) {
		super();
		this.page = page;
		this.sortBy = sortBy;
		this.name = name;
		if(!StringUtils.isEmpty(name) || avgRating!=null || minPrice!=null || maxPrice!=null|| !StringUtils.isEmpty(sortBy) ) {
			this.isFilterApplied = true;
			this.avgRating = avgRating!=null?avgRating:0;
			this.minPrice = minPrice!=null?minPrice:0;
			this.maxPrice = maxPrice!=null?maxPrice:0;
		}else {
			this.isFilterApplied = false;
		}
		
	}
	List<ProductDetails> sortedArray = new ArrayList<>();
	public void  initSort() {
		sortedArray = new ArrayList<>();
	}
	public void sort(ProductDetails pr) {
		
		sortedArray.add(pr);
		/*
		 * if(sortBy.equals("ASC")) { Collections.sort(sortedArray); }else {
		 * Collections.reverse(sortedArray); }
		 * 
		 * if(sortedArray.size()==100) { sortedArray.remove(99); }
		 */
	}
	
	public List<ProductDetails> getSortedArray(){
		 Collections.sort(sortedArray, Comparator.comparing(ProductDetails::getTitle));
		 if(!sortBy.equals("ASC")) { 
			 Collections.reverse(sortedArray);
		 }
			  
		return sortedArray;
	}
	
}
