package dev.rahul.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> createBlog(@PathVariable String userId,@RequestBody Blog blog) {


        boolean isBlogCreated = service.createBlogForUser(userId, blog);

        if (isBlogCreated) {
            return new ResponseEntity<>("Blog created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Blog creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //post mapping for editing blogs using blog id

    @GetMapping
    public List<Blog> getBlogs() {
        return service.getAllBlogs();
    }

    @GetMapping("/{userId}")
    public List<Blog> getUserBlogs(@PathVariable String userId)
    {
        return service.getUserBlogs(userId);
    }

}
