package dev.rahul.blogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "blogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    private ObjectId id;
    private String body;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String imgurl;

    private String buserid; //user id of the user who sent this blog

    @DBRef
    private User user;


    public Blog(String body,LocalDateTime created,LocalDateTime updated,String imgurl)
    {
        this.body=body;
        this.created=created;
        this.updated=updated;
        this.imgurl=imgurl;
    }
}
