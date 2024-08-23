package com.project.chatapp.controller;


import com.project.chatapp.config.TokenProvider;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.User;
import com.project.chatapp.repository.UserRepository;
import com.project.chatapp.request.LoginRequest;
import com.project.chatapp.response.AuthResponse;
import com.project.chatapp.service.CustomerUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository ;
    private PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;
    private CustomerUserService customerUserService;

    public AuthController(  UserRepository userRepository , PasswordEncoder passwordEncoder ,  CustomerUserService customerUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerUserService = customerUserService;
//        this.tokenProvider = tokenProvider;

    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String full_name = user.getFull_name();
        String password = user.getPassword();

//        User isUser = userRepository.findByEmail(email) ;
//        if(isUser != null) {
//            throw new UserException( "Email is used with another account" + email);
//
//        }
        if (userRepository.findByEmail(email) != null) {
            throw new UserException("Email is used with another account: " + email);
        }
        User createdUser = new User() ;
        createdUser.setEmail(email);
        createdUser.setFull_name(full_name);
        createdUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email , password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(jwt , true)  ;
        return  new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);


    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler (@RequestBody LoginRequest req) {

        String email =req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(email , password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(jwt , true);

        return  new ResponseEntity<AuthResponse>(res , HttpStatus.ACCEPTED) ;

    }

    public  Authentication authenticate(String Username , String password) {
        UserDetails userDetails = customerUserService.loadUserByUsername(Username);

        if(userDetails == null ) {
            throw  new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password , userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password or username");
        }
        return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities() );
    }
}
