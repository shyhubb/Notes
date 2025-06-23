package BE.Note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BE.Note.dto.Request.LoginRequest;
import BE.Note.dto.Request.RegisterRequest;
import BE.Note.dto.Response.LoginResponse;
import BE.Note.security.JwtTokenProvider;
import BE.Note.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody RegisterRequest registerRequest) {
        String massage = authServiceImpl.Register(registerRequest.getName(), registerRequest.getAccount(),
                registerRequest.getPassword(), registerRequest.getRepassword());
        if (massage.equals("Create Account Success."))
            return new ResponseEntity<>(massage, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(massage, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest loginRequest) {
        String message = authServiceImpl.Login(loginRequest.getAccount(), loginRequest.getPassword());
        if (message.equals("Login Success.")) {
            String token = jwtTokenProvider.generateToken(loginRequest.getAccount());
            return new ResponseEntity<>(new LoginResponse(message, token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new LoginResponse(message, null), HttpStatus.BAD_REQUEST);
    }

}