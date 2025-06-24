package BE.Note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BE.Note.dto.Request.ChangePassRequest;
import BE.Note.dto.Request.NoteRequest;
import BE.Note.dto.Response.BaseResponse;
import BE.Note.dto.Response.NoteResponse;
import BE.Note.dto.Response.ProfileResponse;
import BE.Note.entities.Note;
import BE.Note.service.impl.CurrentUserDetails;
import BE.Note.service.impl.NoteServiceImpl;
import BE.Note.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    // crud for note
    @Autowired
    private NoteServiceImpl noteServiceImpl;

    @Autowired
    private CurrentUserDetails currentUserDetails;

    @Autowired
    private UserServiceImpl userServiceImpl;

    // create
    @PostMapping("notes/create")
    public ResponseEntity<BaseResponse<String>> createNote(@RequestBody NoteRequest noteRequest) {
        String message = noteServiceImpl.createNote(noteRequest.getTitle(), noteRequest.getContent());
        return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.CREATED);
    }

    // update
    @PostMapping("notes/update/{id}")
    public ResponseEntity<BaseResponse<String>> updateNote(@RequestBody NoteRequest noteRequest,
            @PathVariable Long id) {
        Optional<Note> notetemp = noteServiceImpl.findById(id);
        if (!notetemp.isPresent())
            return new ResponseEntity<>(new BaseResponse<>("Note Does Not Exist.", null), HttpStatus.BAD_REQUEST);
        Note note = notetemp.get();
        if (noteRequest.getTitle() != null)
            note.setTitle(noteRequest.getTitle());
        if (noteRequest.getContent() != null)
            note.setContent(noteRequest.getContent());

        noteServiceImpl.save(note);

        return new ResponseEntity<>(new BaseResponse<>("Update Note Success.", null), HttpStatus.OK);
    }

    // delete
    @PostMapping("/notes/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteNote(@PathVariable Long id) {
        Optional<Note> notetemp = noteServiceImpl.findById(id);
        if (!notetemp.isPresent())
            return new ResponseEntity<>(new BaseResponse<>("Note Does Not Exist.", null), HttpStatus.BAD_REQUEST);
        String message = noteServiceImpl.deleteNote(id);
        if (message.equals("Delete Notes Success.")) {
            return new ResponseEntity<>(new BaseResponse<String>(message, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponse<String>(message, null), HttpStatus.BAD_REQUEST);
    }

    // view all note form this user(resquest)
    @GetMapping("/notes/view")
    public ResponseEntity<BaseResponse<List<NoteResponse>>> detailsNote() {
        List<Note> notes = noteServiceImpl.findByUser(currentUserDetails.getUserDetails());
        if (notes == null)
            return new ResponseEntity<>(new BaseResponse<>("Note Does Not Exist.", null), HttpStatus.BAD_REQUEST);

        List<NoteResponse> viewnotes = new ArrayList<>();
        for (Note note : notes) {
            NoteResponse noteResponse = new NoteResponse();
            noteResponse.setAccount(note.getUser().getAccount());
            noteResponse.setContent(note.getContent());
            noteResponse.setNote_id(note.getId());
            noteResponse.setDate(note.getDateTime());
            noteResponse.setTitle(note.getTitle());
            noteResponse.setUser_id(note.getUser().getId());
            viewnotes.add(noteResponse);
        }

        return new ResponseEntity<>(new BaseResponse<>("Success.", viewnotes), HttpStatus.OK);
    }

    @GetMapping("/notes/view/details/{id}")
    public ResponseEntity<BaseResponse<NoteResponse>> detailsNote(@PathVariable Long id) {
        Optional<Note> notetemp = noteServiceImpl.findById(id);

        if (!notetemp.isPresent()) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Note does not exist.", null),
                    HttpStatus.NOT_FOUND);
        }

        Note note = notetemp.get();
        Long currentUserId = currentUserDetails.getUserDetails().getId();
        Long noteOwnerId = note.getUser().getId();

        if (!noteOwnerId.equals(currentUserId)) {
            return new ResponseEntity<>(
                    new BaseResponse<>("You do not have permission to access this resource.", null),
                    HttpStatus.FORBIDDEN);
        }

        NoteResponse noteResponse = new NoteResponse();
        noteResponse.setAccount(note.getUser().getAccount());
        noteResponse.setContent(note.getContent());
        noteResponse.setNote_id(note.getId());
        noteResponse.setDate(note.getDateTime());
        noteResponse.setTitle(note.getTitle());
        noteResponse.setUser_id(noteOwnerId);

        return new ResponseEntity<>(
                new BaseResponse<>("Success.", noteResponse),
                HttpStatus.OK);
    }

    // endpoint for user profile
    @GetMapping("/profile/view")
    public ResponseEntity<BaseResponse<ProfileResponse>> viewProfile() {
        if (userServiceImpl.viewProfile() != null) {
            return new ResponseEntity<>(new BaseResponse<>("Success.", userServiceImpl.viewProfile()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponse<>("Error.", null), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/profile/changename/{changeName}")
    public ResponseEntity<BaseResponse<String>> changeName(@PathVariable String changeName) {
        // Validate input
        if (changeName == null || changeName.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new BaseResponse<>("Name cannot be empty", null));
        }
        userServiceImpl.changeName(changeName);
        return ResponseEntity.ok(new BaseResponse<>("Success", changeName));
    }

    @GetMapping("/account/delete")
    public ResponseEntity<BaseResponse<String>> deleteAccount() {
        return new ResponseEntity<>(new BaseResponse<>(userServiceImpl.deleteAccount(), null), HttpStatus.OK);
    }

    @PostMapping("/account/changepass")
    public ResponseEntity<BaseResponse<String>> changePassword(@RequestBody ChangePassRequest changePassRequest) {
        String message = userServiceImpl.changePassword(changePassRequest.getChangePass(),
                changePassRequest.getConfirmPass());
        if (message.equals("Change Password Success."))
            return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.OK);
        return new ResponseEntity<>(new BaseResponse<>(message, null), HttpStatus.BAD_REQUEST);
    }
}