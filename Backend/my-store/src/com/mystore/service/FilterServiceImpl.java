package com.mystore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mystore.constants.Constants;
import com.mystore.dto.ProductDetails;
import com.mystore.dto.ProductFilter;

@Component
public class FilterServiceImpl implements FilterService {

	@Override
	public boolean validate(ProductDetails pr, ProductFilter pf) {
		
		boolean isValid = true;
		
		
		
		return isValid;
	}
	@Override
	public List<ProductDetails> search(List<ProductDetails> listData, String name){
		if(name!=null){
			return listData.parallelStream().filter(x->x.getTitle().contains(name)).collect(Collectors.toList());
		}
		return listData;
	}
	@Override
	public List<ProductDetails> filterData(List<ProductDetails> books, ProductFilter pf, int offset, int pageSize) {
		Comparator<ProductDetails> comparator = Comparator.comparing(ProductDetails::getRatings_count);
		if(pf.getSortBy()!=null) {
			if(pf.getSortBy().equals("DSC")) {
				comparator = Comparator.comparing(ProductDetails::getTitle).reversed();
			}else {
				comparator = Comparator.comparing(ProductDetails::getTitle);
			}
		}
		
		List<ProductDetails> result =  books.parallelStream().
				sorted(comparator).filter(x->{
			boolean isValid = true;
			if(pf.getAvgRating()!=0 ) {
				isValid= isValid && x.getAverage_rating()>= pf.getAvgRating() ;
			}
			if(pf.getMaxPrice()!=0 ) {
				isValid = isValid &&  pf.getMaxPrice() >= x.getPrice() ;
			}
			
			if(pf.getMinPrice()!=0 ) {
				isValid =  isValid && x.getPrice()>= pf.getMinPrice() ;
			}
			if(pf.getName()!=null) {
				isValid =  isValid && x.getTitle().contains(pf.getName());
			}
			return isValid;
			
		}).limit(offset+pageSize).collect(Collectors.toList());
		 
				 
		int pageEndOffset =offset+pageSize;
		int results = result.size();
		if(results<offset) {
			return new ArrayList<ProductDetails>();
		}
		pageEndOffset = results>pageEndOffset?pageEndOffset:results;
		 return result.subList(offset, pageEndOffset );
		 
	}

}
