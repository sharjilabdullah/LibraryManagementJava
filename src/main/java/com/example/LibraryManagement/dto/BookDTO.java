package com.example.LibraryManagement.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String category;
    private Boolean available;

    public BookDTO(){}

    public BookDTO(Long id, String title, String author, String category, Boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
