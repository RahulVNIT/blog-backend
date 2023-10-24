package dev.rahul.blogs.Blog;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, ObjectId> {
    List<Blog> findByUserId(String userId);
    List<Blog> findByBusername(String buserid);

    List<Blog> findByTitle(String title);
}
