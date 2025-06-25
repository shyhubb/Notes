package BE.Note.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import BE.Note.dto.Response.NoteResponse;
import BE.Note.entities.Note;
import BE.Note.entities.User;
import BE.Note.repository.NoteRepository;
import BE.Note.repository.UserRepository;
import BE.Note.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteResponse> showAllNote() {
        List<Note> notes = noteRepository.findAll(); // Fetch all notes
        List<NoteResponse> notesResponse = new ArrayList<>();

        for (Note note : notes) {
            if (note.getUser() != null && note.getUser().getAccount() != null) {
                NoteResponse noteResponse = new NoteResponse();
                noteResponse.setAccount(note.getUser().getAccount());
                noteResponse.setUser_id(note.getUser().getId());
                noteResponse.setNote_id(note.getId());
                noteResponse.setContent(note.getContent());
                noteResponse.setTitle(note.getTitle());
                noteResponse.setDate(note.getDateTime());
                notesResponse.add(noteResponse);
            }
        }
        return notesResponse;
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteNote(Long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            noteRepository.delete(note.get());
            return "Note Delete Successfully.";
        }
        return "Note Not Found With ID: " + noteId;
    }

    @Override
    public String deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User Delete Successfully.";
        }
        return "User Not Found With ID: " + userId;
    }

    @Override
    public String changeToAdmin(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setRole("ADMIN"); // Assuming role is a String field in User entity
            userRepository.save(existingUser);
            return "User role changed to ADMIN successfully.";
        }
        return "User Not Found With ID: " + userId;
    }

}