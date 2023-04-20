package com.gene.base.domain.aggregrate.blogAggregrate;

import com.gene.base.domain.aggregrate.LocalEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Comment extends LocalEntity {
    private static final String COMMENT_ID_PREFIX = "cid-";

    private String id;
    private String userId;
    private String parentCommentId;
    private String commentDesc;
    private boolean isDeleted = false;
    private Blog blog;
    private List<Like> likes;

    public Comment(String userId, String parentCommentId, String commentDesc, Blog blog) {
        this.id = COMMENT_ID_PREFIX + UUID.randomUUID();
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.commentDesc = commentDesc;
        this.blog = blog;
    }

    public Comment(String id, String userId, String parentCommentId, String commentDesc, Blog blog) {
        this.id = id;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.commentDesc = commentDesc;
        this.blog = blog;
    }
}
