package com.gene.base.application.command.handler;

import com.gene.base.application.command.CommentBlogCommand;
import com.gene.base.application.exception.BlogServiceException;
import com.gene.base.domain.aggregrate.blogAggregrate.Blog;
import com.gene.base.domain.aggregrate.blogAggregrate.Comment;
import com.gene.base.domain.port.BlogRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

public class CommentBlogCommandHandler implements CommandHandler<CommentBlogCommand> {
    private final BlogRepository blogRepository;

    public CommentBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NotNull CommentBlogCommand commentBlogCommand) {
        Blog blog = blogRepository.findById(commentBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        if (commentBlogCommand.getParentCommentId() != null) {
            List<Comment> comments = blog.getComments()
                    .stream()
                    .filter(comment -> comment.getParentCommentId().equals(commentBlogCommand.getParentCommentId()))
                    .toList();
            if (comments == null || comments.size() == 0) {
                throw new IllegalArgumentException("Parent comment ID is invalid!");
            }
        }
        try {
            blogRepository.saveDeleteComment(blog.addComment(commentBlogCommand.getUserId(), commentBlogCommand.getParentCommentId(), commentBlogCommand.getCommentDesc(), blog), true);
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while creating the comment: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
