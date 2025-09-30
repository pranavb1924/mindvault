package com.mindvault.mindvault.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateEncryptedEntryRequest {

    @NotBlank(message = "Title IV is required")
    private String titleIv;

    @NotBlank(message = "Title cipher is required")
    private String titleCipher;

    @NotBlank(message = "Title tag is required")
    private String titleTag;

    @NotBlank(message = "Body IV is required")
    private String bodyIv;

    @NotBlank(message = "Body cipher is required")
    private String bodyCipher;

    @NotBlank(message = "Body tag is required")
    private String bodyTag;

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
}