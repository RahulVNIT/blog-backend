package dev.rahul.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean createBlogForUser(String userId, Blog blog) {
        // Retrieve the user from the database using the userId
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUserId(userId));

        if (optionalUser.isEmpty()) {
            return false; // User not found, return false to indicate failure
        }

        User user = optionalUser.get();
        blog.setBuserid(userId);
        blog.setCreated(LocalDateTime.now());
        blog.setUpdated(LocalDateTime.now());
        System.out.println(blog);

        // Associate the user with the blog
        blog.setUser(user);

        // Save the blog to the database
        Blog savedBlog = blogRepository.save(blog);

        return true; // Return true if the blog was successfully saved
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getUserBlogs(String userId) {
        return blogRepository.findByBuserid(userId);
    }
}
