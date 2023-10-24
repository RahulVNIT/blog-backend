package dev.rahul.blogs.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            User newuser=userRepository.findUserByUsername((user.getUsername()));
            if(Objects.equals(newuser.getUsername(), user.getUsername())) {
                System.out.println("USERNAME TAKEN");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username already taken");
            }
            else
            {
                throw new RuntimeException("exception occured");
            }
        }
        catch(Exception e)
        {
            userRepository.save(user);
            System.out.println("New User successfully registered");
            return ResponseEntity.ok("registration successful");
        }
    }

    public ResponseEntity<String> loginUser(User user) {
        try{
            User olduser=userRepository.findUserByUsername((user.getUsername()));
            if(Objects.equals(olduser.getPassword(), user.getPassword()))
            {
                return ResponseEntity.ok("login successful");
            }
            else
            {
                System.out.println("Wrong Password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
            }
        }
        catch (Exception e)
        {
            System.out.println("Invalid Username");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }
    }


}
