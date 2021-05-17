package com.example.bookloop2;

public class Book {

    private long ID;
    private String title;
    private String author;
    private int rating;
    private String review;
    private String date;

    Book() {}

    Book (String title, String author, int rating, String review, String date){

        this.title = title;
        this.author = author;
        this.rating = rating;
        this.review = review;
        this.date = date;

    }

    Book (long ID, String title, String author, int rating, String review, String date){

        this.ID = ID;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.review = review;
        this.date = date;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
