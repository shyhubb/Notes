package BE.Note.service.interfaces;

import BE.Note.dto.Response.ProfileResponse;

public interface UserService {
    public ProfileResponse viewProfile();

    public String changeName(String changeName);

    public String changePassword(String changePass, String confirmPass);

    public String deleteAccount();
}
