package com.gene.base.application.command;

import io.jkratz.mediator.core.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteCommentBlogCommand implements Command {
    private String blogId;
    private String userId;
    private String commentId;
    private String parentCommentId;

    public DeleteCommentBlogCommand(String blogId, String userId, String commentId) {
        this.blogId = blogId;
        this.userId = userId;
        this.commentId = commentId;
    }
}
