package BE.Note.dto.Response;

import java.time.LocalDateTime;

public class NoteResponse {
    private Long note_id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String account;
    private Long user_id;

    public NoteResponse() {
    }

    public NoteResponse(Long note_id, String title, String content, LocalDateTime date, String account, Long user_id) {
        this.note_id = note_id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.account = account;
        this.user_id = user_id;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
