package BE.Note.service.interfaces;

import java.util.List;
import java.util.Optional;
import BE.Note.entities.Note;
import BE.Note.entities.User;

public interface NoteService {

    public String createNote(String title, String content);

    public Optional<Note> findById(Long id);

    public void save(Note note);

    public void deleteNote(Long id);

    public List<Note> findByUser(User user);

    public void deleteNoteByUser(User user);

}
