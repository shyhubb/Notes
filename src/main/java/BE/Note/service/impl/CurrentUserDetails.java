package BE.Note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import BE.Note.entities.User;
import BE.Note.repository.UserRepository;

@Service
public class CurrentUserDetails {
    @Autowired
    private UserRepository userRepository;

    // lay user trong request
    public User getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Lấy UserDetails từ Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByAccount(userDetails.getUsername());
        return user;
    }
}
