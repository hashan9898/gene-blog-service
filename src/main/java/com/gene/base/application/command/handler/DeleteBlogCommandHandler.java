package com.gene.base.application.command.handler;

import com.gene.base.application.command.DeleteBlogCommand;
import com.gene.base.application.exception.BlogServiceException;
import com.gene.base.domain.aggregrate.blogAggregrate.Blog;
import com.gene.base.domain.port.BlogRepository;
import io.jkratz.mediator.core.CommandHandler;
import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;

public class DeleteBlogCommandHandler implements CommandHandler<DeleteBlogCommand> {

    private final BlogRepository blogRepository;

    public DeleteBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NonNull DeleteBlogCommand deleteBlogCommand) {
        Blog blog = blogRepository.findById(deleteBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        try {
            blogRepository.deleteById(deleteBlogCommand.getBlogId());
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while deleting the blog: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
