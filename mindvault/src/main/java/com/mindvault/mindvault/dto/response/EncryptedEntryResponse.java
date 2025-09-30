package com.mindvault.mindvault.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class EncryptedEntryResponse {

    private UUID id;
    private String titleIv;
    private String titleCipher;
    private String titleTag;
    private String bodyIv;
    private String bodyCipher;
    private String bodyTag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitleIv() {
        return titleIv;
    }

    public void setTitleIv(String titleIv) {
        this.titleIv = titleIv;
    }

    public String getTitleCipher() {
        return titleCipher;
    }

    public void setTitleCipher(String titleCipher) {
        this.titleCipher = titleCipher;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    public String getBodyIv() {
        return bodyIv;
    }

    public void setBodyIv(String bodyIv) {
        this.bodyIv = bodyIv;
    }

    public String getBodyCipher() {
        return bodyCipher;
    }

    public void setBodyCipher(String bodyCipher) {
        this.bodyCipher = bodyCipher;
    }

    public String getBodyTag() {
        return bodyTag;
    }

    public void setBodyTag(String bodyTag) {
        this.bodyTag = bodyTag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}