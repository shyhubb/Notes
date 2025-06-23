package BE.Note.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import BE.Note.dto.Response.ProfileResponse;
import BE.Note.entities.User;
import BE.Note.repository.UserRepository;
import BE.Note.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserDetails currentUserDetails;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse viewProfile() {
        Long id = currentUserDetails.getUserDetails().getId();
        Optional<User> temp = userRepository.findById(id);
        if (temp.isPresent()) {
            return new ProfileResponse(temp.get().getName(), temp.get().getAccount());
        }
        return null;
    }

    @Override
    public String changeName(String changeName) {
        Long id = currentUserDetails.getUserDetails().getId();
        Optional<User> temp = userRepository.findById(id);
        if (temp.isPresent()) {
            User user = temp.get();
            user.setName(changeName);
            userRepository.save(user);
            return "Success.";
        }
        return "Account Not Found , Please Login Again.";
    }

    @Override
    public String changePassword(String changePass, String confirmPass) {
        if (!changePass.equals(confirmPass))
            return "Confirm Pass Does Not Match.";
        Long id = currentUserDetails.getUserDetails().getId();
        Optional<User> temp = userRepository.findById(id);
        User user = temp.get();
        changePass = passwordEncoder.encode(changePass);
        user.setPassword(changePass);
        userRepository.save(user);
        return "Change Password Success.";
    }

    @Override
    public String deleteAccount() {
        Long id = currentUserDetails.getUserDetails().getId();
        userRepository.deleteById(id);
        return "Success.";
    }

}
