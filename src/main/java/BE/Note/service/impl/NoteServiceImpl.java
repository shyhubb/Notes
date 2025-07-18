package BE.Note.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BE.Note.entities.Note;
import BE.Note.entities.User;
import BE.Note.repository.NoteRepository;
import BE.Note.service.interfaces.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private CurrentUserDetails currentUserDetails;
    @Autowired
    private NoteRepository noteRepository;

    public String createNote(String title, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setTitle(title);
        LocalDateTime now = LocalDateTime.now();
        note.setDateTime(now);
        User user = currentUserDetails.getUserDetails();
        note.setUser(user);

        noteRepository.save(note);

        return "Create Note Success.";
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public String deleteNote(Long id) {
        User user = currentUserDetails.getUserDetails();
        if (user.equals(noteRepository.findByUser(user))) {
            Optional<Note> nOptional = noteRepository.findById(id);
            if (nOptional.isPresent()) {
                noteRepository.deleteById(id);
                return "Delete Notes Success.";
            }
        }
        return "You Do Not Have Permission To Access This Note.";
    }

    @Override
    public List<Note> findByUser(User user) {
        return noteRepository.findByUser(user);
    }

    @Override
    public void deleteNoteByUser(User user) {
        noteRepository.deleteByUser(user);
    }

}
