package com.gene.base.application.command.handler;

import com.gene.base.application.command.DeleteCommentBlogCommand;
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

public class DeleteCommentBlogCommandHandler implements CommandHandler<DeleteCommentBlogCommand> {
    private final BlogRepository blogRepository;

    public DeleteCommentBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NotNull DeleteCommentBlogCommand deleteCommentBlogCommand) {
        Blog blog = blogRepository.findById(deleteCommentBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        List<Comment> comments = blog.getComments()
                .stream()
                .filter(comment -> comment.getId().equals(deleteCommentBlogCommand.getCommentId()))
                .toList();
        if (comments == null || comments.size() == 0) {
            throw new IllegalArgumentException("Comment ID is invalid!");
        }
        blog.getComments().remove(comments.get(0));
        try {
            blogRepository.saveDeleteComment(blog, false);
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while deleting the comment: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
