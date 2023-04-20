package com.gene.base.application.command.handler;

import com.gene.base.application.command.UpdateCommentBlogCommand;
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

public class UpdateCommentBlogCommandHandler implements CommandHandler<UpdateCommentBlogCommand> {
    private final BlogRepository blogRepository;

    public UpdateCommentBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NotNull UpdateCommentBlogCommand updateCommentBlogCommand) {
        Blog blog = blogRepository.findById(updateCommentBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        if (updateCommentBlogCommand.getParentCommentId() != null) {
            List<Comment> comments = blog.getComments()
                    .stream()
                    .filter(comment -> comment.getParentCommentId().equals(updateCommentBlogCommand.getParentCommentId()))
                    .toList();
            if (comments == null || comments.size() == 0) {
                throw new IllegalArgumentException("Parent comment ID is invalid!");
            }
        }
        List<Comment> comments = blog.getComments()
                .stream()
                .filter(comment -> comment.getId().equals(updateCommentBlogCommand.getCommentId()))
                .toList();
        if (comments == null || comments.size() == 0) {
            throw new IllegalArgumentException("Comment ID is invalid!");
        }
        blog.getComments().stream().forEach(comment -> {
            if(comment.getId().equals(updateCommentBlogCommand.getCommentId())){
                comment.setCommentDesc(updateCommentBlogCommand.getCommentDesc());
            }
        });
        try {
            blogRepository.saveDeleteComment(blog, false);
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while updating the comment: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
