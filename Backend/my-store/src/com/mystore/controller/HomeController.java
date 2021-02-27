package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mystore.service.*;
import com.mystore.dto.*;
import java.util.*;


@RestController
@Controller
@RequestMapping("Products")
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	 @RequestMapping(method = RequestMethod.GET)
	public  @ResponseBody List<ProductDetails> Get(@RequestParam int page, @RequestParam(required=false, defaultValue = "") String name, @RequestParam(required=false) Long maxPrice,@RequestParam(required=false) Long minPrice, 
			@RequestParam(required=false) Long avgRating, @RequestParam(required=false) String sortBy) throws Exception {
		 return productService.getProducts(new ProductFilter( page, sortBy,name, avgRating,minPrice, maxPrice ));
	}
}
