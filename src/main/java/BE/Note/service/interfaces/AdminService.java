package BE.Note.service.interfaces;

import java.util.List;

import BE.Note.dto.Response.NoteResponse;
import BE.Note.entities.User;

public interface AdminService {
    public List<NoteResponse> showAllNote();

    public List<User> showAllUsers();

    public String deleteNote(Long noteId);

    public String deleteUser(Long userId);

    public String changeToAdmin(Long userId);

}
