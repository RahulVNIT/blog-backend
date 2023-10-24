package dev.rahul.blogs.Blog;

import dev.rahul.blogs.Blog.BlogService;
import dev.rahul.blogs.User.UserService;
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

    @PostMapping("/{username}")
    public ResponseEntity<String> createBlog(@PathVariable String username,@RequestBody Blog blog) {


        boolean isBlogCreated = service.createBlogForUser(username, blog);

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

    @GetMapping("/{username}")
    public List<Blog> getUserBlogs(@PathVariable String username)
    {
        System.out.println(username);
        return service.getUserBlogs(username);
    }

    @GetMapping("/title/{title}")
    public List<Blog> getsomeBlogs(@PathVariable String title)
    {
        return service.gettitleblogs(title);
    }

}

