package com.gene.base.application.command.handler;

import com.gene.base.application.command.LikeBlogCommand;
import com.gene.base.application.exception.BlogServiceException;
import com.gene.base.domain.aggregrate.blogAggregrate.Blog;
import com.gene.base.domain.port.BlogRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

public class LikeBlogCommandHandler implements CommandHandler<LikeBlogCommand> {
    private final BlogRepository blogRepository;

    public LikeBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NotNull LikeBlogCommand likeBlogCommand) {
        Blog blog = blogRepository.findById(likeBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        try {
            blogRepository.addRemoveLike(blog.addLike(blog,likeBlogCommand.getUserId()), true);
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while the like: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
