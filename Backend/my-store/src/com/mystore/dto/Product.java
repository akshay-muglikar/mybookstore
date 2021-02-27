package com.mystore.dto;

public class Product {
	private long bookID;
	private String title;
	private String authors;
    private float average_rating;
    private long isbn;
    private String language_code;
    private long ratings_count;
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
	public float getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(float average_rating) {
		this.average_rating = average_rating;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
	public long getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(long ratings_count) {
		this.ratings_count = ratings_count;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
