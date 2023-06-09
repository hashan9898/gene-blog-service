package com.gene.base.application.command;

import io.jkratz.mediator.core.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateCommentBlogCommand implements Command {
    private String blogId;
    private String userId;
    private String commentId;

    private String parentCommentId;

    @NotNull(message = "Comment description should not be null!")
    @NotEmpty(message = "Comment description should not be empty!")
    @Size(max = 225, message = "Comment description should be max 225 characters!")
    private String commentDesc;
}
