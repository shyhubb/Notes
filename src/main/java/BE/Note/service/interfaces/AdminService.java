package BE.Note.service.interfaces;

import java.util.List;

import BE.Note.dto.Response.NoteResponse;

public interface AdminService {
    public List<NoteResponse> showAllNote();
}
