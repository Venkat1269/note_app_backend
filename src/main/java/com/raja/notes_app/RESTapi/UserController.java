package com.raja.notes_app.RESTapi;

import com.raja.notes_app.entity.AppUser;
import com.raja.notes_app.entity.Note;
import com.raja.notes_app.model.LoginData;
import com.raja.notes_app.model.UserModel;
import com.raja.notes_app.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AppUserService appUserService;
    @PostMapping("/login")
    public AppUser login(@RequestBody LoginData loginData) {
        return appUserService.login(loginData);
    }

    @PostMapping("/signin")
    public AppUser signIn(@RequestBody UserModel model) {
        return appUserService.signIn(model);
    }

    @GetMapping("/{userId}")
    public List<Note> getNotes(@PathVariable Long userId) {
        return appUserService.getAllNotes(userId);
    }

    @PostMapping("/{userId}")
    public Note addNote(@RequestBody Note note, @PathVariable Long userId) {
        return appUserService.addNote(userId, note);
    }

    @PutMapping
    public Note updateNote(@RequestBody Note note) {
        return appUserService.updateNote(note);
    }
    @DeleteMapping
    public void deleteNote(@RequestParam Long noteId, @RequestParam Long userId) {
        appUserService.deleteNote(userId, noteId);
    }

    @GetMapping("/{userId}/search")
    public List<Note> searchNote(@PathVariable Long userId, @RequestParam String tag, @RequestParam String text) {
        return appUserService.searchNotes(userId, tag, text);
    }
}
