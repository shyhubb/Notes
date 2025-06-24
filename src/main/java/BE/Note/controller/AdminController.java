package BE.Note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BE.Note.dto.Response.BaseResponse;
import BE.Note.dto.Response.NoteResponse;
import BE.Note.service.impl.AdminServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @GetMapping("/notes/showall")
    public ResponseEntity<BaseResponse<List<NoteResponse>>> showAllNote() {
        List<NoteResponse> notes = adminServiceImpl.showAllNote();
        if (notes == null || notes.isEmpty()) { // Check for null or empty list
            return new ResponseEntity<>(new BaseResponse<>("No notes found.", null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BaseResponse<>("Successfully retrieved all notes.", notes), HttpStatus.OK);
    }
}