package BE.Note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<NoteResponse>> showAllNote() {
        List<NoteResponse> notes = adminServiceImpl.showAllNote();
        if (notes == null)
            return new ResponseEntity<>(notes, HttpStatus.BAD_REQUEST);
        System.out.println(notes);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

}
