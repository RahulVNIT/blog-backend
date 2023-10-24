package dev.rahul.blogs.Blog;

import dev.rahul.blogs.Blog.BlogRepository;
import dev.rahul.blogs.User.User;
import dev.rahul.blogs.User.UserRepository;
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

    public boolean createBlogForUser(String username, Blog blog) {
        // Retrieve the user from the database using the userId
        User bloguser = userRepository.findUserByUsername(username);
        blog.setBusername(username);
        blog.setCreated(LocalDateTime.now());
        blog.setUpdated(LocalDateTime.now());
        System.out.println(blog);

        blog.setUser(bloguser);

        Blog savedBlog = blogRepository.save(blog);

        return true; // Return true if the blog was successfully saved
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getUserBlogs(String username) {
        return blogRepository.findByBusername(username);
    }

    public List<Blog> gettitleblogs(String title)
    {
        return blogRepository.findByTitle(title);
    }
}

