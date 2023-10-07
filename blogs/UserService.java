package dev.rahul.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
        }
        catch(Exception e)
        {
            System.out.println("ERROR HERE");
        }
        return user;

    }

    public User loginUser(User user) {
        if(userRepository.findUserByUsername(user.getUsername())!=null)
        {
            return user;
        }
        // Implement user authentication logic here
        // Compare the encoded password with the stored password hash
        // Return the user if authentication is successful
        // Otherwise, return null or handle authentication failure
        return user;
    }
}
