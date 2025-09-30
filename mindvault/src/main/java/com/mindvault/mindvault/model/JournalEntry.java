package com.mindvault.mindvault.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "journal_entries")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "title_iv", nullable = false)
    @Lob
    private byte[] titleIv;

    @Column(name = "title_cipher", nullable = false)
    @Lob
    private byte[] titleCipher;

    @Column(name = "title_tag", nullable = false)
    @Lob
    private byte[] titleTag;

    @Column(name = "body_iv", nullable = false)
    @Lob
    private byte[] bodyIv;

    @Column(name = "body_cipher", nullable = false)
    @Lob
    private byte[] bodyCipher;

    @Column(name = "body_tag", nullable = false)
    @Lob
    private byte[] bodyTag;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public JournalEntry() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getTitleIv() {
        return titleIv;
    }

    public void setTitleIv(byte[] titleIv) {
        this.titleIv = titleIv;
    }

    public byte[] getTitleCipher() {
        return titleCipher;
    }

    public void setTitleCipher(byte[] titleCipher) {
        this.titleCipher = titleCipher;
    }

    public byte[] getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(byte[] titleTag) {
        this.titleTag = titleTag;
    }

    public byte[] getBodyIv() {
        return bodyIv;
    }

    public void setBodyIv(byte[] bodyIv) {
        this.bodyIv = bodyIv;
    }

    public byte[] getBodyCipher() {
        return bodyCipher;
    }

    public void setBodyCipher(byte[] bodyCipher) {
        this.bodyCipher = bodyCipher;
    }

    public byte[] getBodyTag() {
        return bodyTag;
    }

    public void setBodyTag(byte[] bodyTag) {
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}