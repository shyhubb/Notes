package BE.Note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import BE.Note.entities.User;
import BE.Note.repository.UserRepository;
import BE.Note.service.interfaces.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String Login(String account, String password) {
        User user = userRepository.findByAccount(account);
        if (user == null)
            return "Account or password incorrect.";
        if (passwordEncoder.matches(password, user.getPassword()))
            return "Login Success.";
        return "Account or password incorrect.";
    }

    @Override
    public String Register(String name, String account, String password, String repassword) {
        if (!password.equals(repassword))
            return "Repassword Does Not Match.";
        if (userRepository.findByAccount(account) != null)
            return String.format("Account '%s' already exists. Please choose another account.", account);
        password = passwordEncoder.encode(password);
        User user = new User(name, account, password);
        userRepository.save(user);
        return "Create Account Success.";

    }

}
