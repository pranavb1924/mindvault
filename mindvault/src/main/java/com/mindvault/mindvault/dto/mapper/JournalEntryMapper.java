package com.mindvault.mindvault.dto.mapper;

import com.mindvault.mindvault.dto.request.EncryptedEntryRequest;
import com.mindvault.mindvault.dto.response.EncryptedEntryResponse;
import com.mindvault.mindvault.model.JournalEntry;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JournalEntryMapper {

    public JournalEntry toEntity(EncryptedEntryRequest request) {
        JournalEntry entry = new JournalEntry();
        entry.setTitleIv(Base64.getDecoder().decode(request.getTitleIv()));
        entry.setTitleCipher(Base64.getDecoder().decode(request.getTitleCipher()));
        entry.setTitleTag(Base64.getDecoder().decode(request.getTitleTag()));
        entry.setBodyIv(Base64.getDecoder().decode(request.getBodyIv()));
        entry.setBodyCipher(Base64.getDecoder().decode(request.getBodyCipher()));
        entry.setBodyTag(Base64.getDecoder().decode(request.getBodyTag()));
        return entry;
    }

    public void updateEntity(JournalEntry entry, EncryptedEntryRequest request) {
        entry.setTitleIv(Base64.getDecoder().decode(request.getTitleIv()));
        entry.setTitleCipher(Base64.getDecoder().decode(request.getTitleCipher()));
        entry.setTitleTag(Base64.getDecoder().decode(request.getTitleTag()));
        entry.setBodyIv(Base64.getDecoder().decode(request.getBodyIv()));
        entry.setBodyCipher(Base64.getDecoder().decode(request.getBodyCipher()));
        entry.setBodyTag(Base64.getDecoder().decode(request.getBodyTag()));
    }

    public EncryptedEntryResponse toResponse(JournalEntry entry) {
        EncryptedEntryResponse response = new EncryptedEntryResponse();
        response.setId(entry.getId());
        response.setTitleIv(Base64.getEncoder().encodeToString(entry.getTitleIv()));
        response.setTitleCipher(Base64.getEncoder().encodeToString(entry.getTitleCipher()));
        response.setTitleTag(Base64.getEncoder().encodeToString(entry.getTitleTag()));
        response.setBodyIv(Base64.getEncoder().encodeToString(entry.getBodyIv()));
        response.setBodyCipher(Base64.getEncoder().encodeToString(entry.getBodyCipher()));
        response.setBodyTag(Base64.getEncoder().encodeToString(entry.getBodyTag()));
        response.setCreatedAt(entry.getCreatedAt());
        response.setUpdatedAt(entry.getUpdatedAt());
        return response;
    }

    public List<EncryptedEntryResponse> toResponseList(List<JournalEntry> entries) {
        return entries.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}