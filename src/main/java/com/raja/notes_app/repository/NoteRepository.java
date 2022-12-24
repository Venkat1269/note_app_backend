package com.raja.notes_app.repository;

import com.raja.notes_app.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
