// src/main/java/com/mindvault/mindvault/repository/JournalEntryRepository.java
package com.mindvault.mindvault.repository;

import com.mindvault.mindvault.model.JournalEntry;
import com.mindvault.mindvault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findAllByUserOrderByCreatedAtDesc(User user);

}
