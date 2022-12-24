package com.raja.notes_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    public void addNote(Note note) {
        if(notes == null) {
            notes = new ArrayList<Note>();
        }
        notes.add(note);
    }

    public void removeNote(Long id) {
        if(notes != null) {
            notes.removeIf((Note note) -> note.getId().equals(id));
        }
    }
}
