// src/main/java/com/mindvault/mindvault/dto/JournalEntryRequest.java
package com.mindvault.mindvault.dto;

public class JournalEntryRequest {
    private String title;
    private String content;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
