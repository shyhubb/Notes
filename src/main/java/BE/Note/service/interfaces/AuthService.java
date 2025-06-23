package BE.Note.service.interfaces;

public interface AuthService {
    public String Login(String account, String password);

    public String Register(String name, String account, String password, String repassword);
}
