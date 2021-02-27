package com.mystore.dto;

public class ProductDetails implements Comparable<ProductDetails>  {

	private long bookID;
	private String title;
	private String authors;
    private String average_rating;
    private String isbn;
    private String language_code;
    private String ratings_count;
    private long price;
    
	public long getBookID() {
		return bookID;
	}
	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public double getAverage_rating() {
		try {
			return Double.parseDouble(average_rating);
		}catch(NumberFormatException e) {
			return 0;
		}
				
	}
	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
	public String getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	@Override
	public int compareTo(ProductDetails arg0) {
		 if (getTitle() == null || arg0.getTitle() == null) {
		      return 0;
		    }
		return getTitle().compareToIgnoreCase(arg0.getTitle());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
    
	
		
}
