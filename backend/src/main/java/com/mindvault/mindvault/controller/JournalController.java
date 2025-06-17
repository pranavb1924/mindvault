
package com.mindvault.mindvault.controller;

import com.mindvault.mindvault.model.JournalEntry;
import com.mindvault.mindvault.model.User;
import com.mindvault.mindvault.repository.JournalEntryRepository;
import com.mindvault.mindvault.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class JournalController {

    private final JournalEntryRepository entries;
    private final UserRepository users;

    public JournalController(JournalEntryRepository entries, UserRepository users) {
        this.entries = entries; this.users = users;
    }

    @GetMapping("/{userId}")
    public List<JournalEntry> all(@PathVariable Long userId) {
        return entries.findAllByUserOrderByCreatedAtDesc(
                users.findById(userId).orElseThrow());
    }

    @PostMapping("/{userId}")
    public JournalEntry create(@PathVariable Long userId,
                               @RequestBody JournalEntry in) {
        in.setUser(users.findById(userId).orElseThrow());
        JournalEntry saved = entries.save(in);
        return saved;   // ensure this has title + content + createdAt
    }

    @PutMapping("/{userId}/{entryId}")
    public JournalEntry update(
            @PathVariable Long userId,
            @PathVariable Long entryId,
            @RequestBody JournalEntry in
    ) {
        // ensure it’s the same user
        User user = users.findById(userId).orElseThrow();
        JournalEntry existing = entries.findById(entryId).orElseThrow();
        if (!existing.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        // copy over fields
        existing.setTitle(in.getTitle());
        existing.setContent(in.getContent());
        //existing.setContent(LocalDateTime.now());
        return entries.save(existing);
    }

    @DeleteMapping("/{userId}/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(
            @PathVariable Long userId,
            @PathVariable Long entryId
    ) {
        User user = users.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        JournalEntry entry = entries.findById(entryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!entry.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        entries.delete(entry);
    }
}
