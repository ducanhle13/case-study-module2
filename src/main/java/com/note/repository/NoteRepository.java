package com.note.repository;

import com.note.model.Category;
import com.note.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note,Long> {

    Page<Note> findAllByTitleContainingOrContentContaining(String title, String content,Pageable pageable);


}