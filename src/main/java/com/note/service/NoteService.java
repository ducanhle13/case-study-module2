package com.note.service;

import com.note.model.Category;
import com.note.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoteService {
    Page<Note> findAll(Pageable pageable);

    Optional<Note> findById(Long id);
    void save(Note note);
    void delete(Long id);
    Page<Note> findAllByTitleContainingOrContentContaining(String title, String content ,Pageable pageable);
}
