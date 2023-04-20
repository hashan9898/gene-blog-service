package com.gene.base.domain.aggregrate.blogAggregrate;

import com.gene.base.domain.aggregrate.AggregateRoot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Blog extends AggregateRoot {
    private static final String BLOG_ID_PREFIX = "bid-";

    private String id;
    private String content;
    private String title;
    private String description;
    private String location;
    private boolean isDeleted = false;
    private List<Comment> comments;
    private List<Like> likes;

    public Blog(String content, String title, String description, String location) {
        super();
        this.id = BLOG_ID_PREFIX + UUID.randomUUID();
        this.content = content;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public Blog addComment(String userId, String parentCommentId, String commentDesc, Blog blog) {
        blog.getComments().add(new Comment(userId, parentCommentId, commentDesc, blog));
        return blog;
    }

    public Blog(String id) {
        this.id = id;
    }

    public Blog addLike(Blog blog, String userId) {
        blog.getLikes().add(new Like(userId, blog));
        return blog;
    }
}
