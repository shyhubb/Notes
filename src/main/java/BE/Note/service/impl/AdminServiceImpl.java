package BE.Note.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BE.Note.dto.Response.NoteResponse;
import BE.Note.entities.Note;
import BE.Note.repository.NoteRepository;
import BE.Note.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private NoteRepository noteRepository;

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

}