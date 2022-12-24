package com.raja.notes_app.service;

import com.raja.notes_app.entity.AppUser;
import com.raja.notes_app.entity.Note;
import com.raja.notes_app.model.LoginData;
import com.raja.notes_app.model.UserModel;

import java.util.List;

public interface AppUserService {

    public AppUser login(LoginData loginData);
    public AppUser signIn(UserModel userData);
    public List<Note> getAllNotes(Long id);
    public Note addNote(Long id, Note note);
    public Note updateNote(Note note);
    public void deleteNote(Long userId, Long noteId);
    public List<Note> searchNotes(Long id, String type, String text);
}
