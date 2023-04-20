package com.gene.base.application.command;

import io.jkratz.mediator.core.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteBlogCommand implements Command {
    private String blogId;

    public DeleteBlogCommand(String blogId) {
        this.blogId = blogId;
    }
}
