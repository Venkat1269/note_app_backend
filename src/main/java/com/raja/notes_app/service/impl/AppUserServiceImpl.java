package com.raja.notes_app.service.impl;

import com.raja.notes_app.entity.AppUser;
import com.raja.notes_app.entity.Note;
import com.raja.notes_app.entity.Tag;
import com.raja.notes_app.exceptionHandler.exception.DataException;
import com.raja.notes_app.exceptionHandler.exception.NotFoundException;
import com.raja.notes_app.model.LoginData;
import com.raja.notes_app.model.UserModel;
import com.raja.notes_app.repository.AppUserRepository;
import com.raja.notes_app.repository.NoteRepository;
import com.raja.notes_app.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final NoteRepository noteRepository;

    @Override
    public AppUser login(LoginData loginData) {
        var userDetail = appUserRepository.findByEmail(loginData.getEmail());
        if(userDetail.isPresent()) {
            var user = userDetail.get();
            if(user.getPassword().equals(loginData.getPassword())) {
                return user;
            }
            throw new DataException("email id or password didn't match");
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public AppUser signIn(UserModel userData) {
        if(userData.getEmail() == null) {
            throw new DataException("email is a required parameter");
        }
        if(userData.getName() == null) {
            throw new DataException("name is a required parameter");
        }
        if(userData.getPassword() == null) {
            throw new DataException("password is a required parameter");
        }
        AppUser user = new AppUser();
        user.setEmail(userData.getEmail());
        user.setName(userData.getName());
        user.setPassword(userData.getPassword());
        return appUserRepository.save(user);
    }

    @Override
    public List<Note> getAllNotes(Long id) {

        var userDetail = appUserRepository.findById(id);
        if(userDetail.isPresent()) {
            return userDetail.get().getNotes();
        }
        throw new NotFoundException(id);
    }

    @Override
    public Note addNote(Long id, Note note) {
        var userDetail = appUserRepository.findById(id);
        if(userDetail.isPresent()) {
            var user = userDetail.get();
            Note savedNote = noteRepository.save(note);
            user.addNote(savedNote);
            appUserRepository.save(user);
            return savedNote;
        }
        throw new NotFoundException(id);
    }

    @Override
    public Note updateNote(Note note) {
        if(note.getId() == null) {
            throw new DataException("Id is a required argument for update");
        }
        if(noteRepository.findById(note.getId()).isEmpty()) {
            throw new NotFoundException("Note not found");
        }
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long userId, Long noteId) {
        if(noteId == null) {
            throw new DataException("Id is a required argument for update");
        }
        if(noteRepository.findById(noteId).isEmpty()) {
            throw new NotFoundException("Note not found");
        }
        var userDetail = appUserRepository.findById(userId);
        if(userDetail.isPresent()) {
            var user = userDetail.get();
            user.removeNote(noteId);
            appUserRepository.save(user);
            noteRepository.deleteById(noteId);
            return;
        }
        throw new NotFoundException(userId);
    }

    @Override
    public List<Note> searchNotes(Long id, String type, String text) {
        var userDetail = appUserRepository.findById(id);
        if(userDetail.isPresent()) {
            var user = userDetail.get();
            if(type.equalsIgnoreCase("tag")) {
                return user.getNotes().stream().filter((Note note) -> note.getTags().contains(new Tag(text))).collect(Collectors.toList());
            } else if(type.equalsIgnoreCase("name")) {
                return user.getNotes().stream().filter((Note note) -> note.getName().toLowerCase().contains(text)).collect(Collectors.toList());
            } else if(type.equalsIgnoreCase("text")) {
                return user.getNotes().stream().filter((Note note) -> note.getNoteText().toLowerCase().contains(text)).collect(Collectors.toList());
            }
            throw new DataException("type not defined choose from tag, name and text");
        }
        throw new NotFoundException(id);

    }
}
