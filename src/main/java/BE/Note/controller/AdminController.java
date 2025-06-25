package BE.Note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BE.Note.dto.Response.BaseResponse;
import BE.Note.dto.Response.NoteResponse;
import BE.Note.entities.User;
import BE.Note.service.impl.AdminServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/user/showall")
    public ResponseEntity<BaseResponse<List<User>>> showAllUsers() {
        List<User> users = adminServiceImpl.showAllUsers();
        if (users == null || users.isEmpty()) { // Check for null or empty list
            return new ResponseEntity<>(new BaseResponse<>("No users found.", null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BaseResponse<>("Successfully retrieved all users.", users), HttpStatus.OK);
    }

    @GetMapping("/note/delete")
    public ResponseEntity<BaseResponse<String>> deleteNote(Long noteId) {
        String message = adminServiceImpl.deleteNote(noteId);
        if (message.contains("Not Found")) {
            return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.OK);
    }

    @GetMapping("/user/delete")
    public ResponseEntity<BaseResponse<String>> deleteUser(Long userId) {
        String message = adminServiceImpl.deleteUser(userId);
        if (message.contains("Not Found")) {
            return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.OK);
    }

    @PutMapping("user/changetoadmin/{id}")
    public ResponseEntity<BaseResponse<String>> changeToAdmin(@PathVariable("id") Long userId) {
        String message = adminServiceImpl.changeToAdmin(userId);
        if (message.contains("Not Found")) {
            return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.OK);
    }

}