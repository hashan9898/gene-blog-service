package com.gene.base.application.command.handler;

import com.gene.base.application.command.UnlikeBlogCommand;
import com.gene.base.application.exception.BlogServiceException;
import com.gene.base.domain.aggregrate.blogAggregrate.Blog;
import com.gene.base.domain.aggregrate.blogAggregrate.Like;
import com.gene.base.domain.port.BlogRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

public class UnlikeBlogCommandHandler implements CommandHandler<UnlikeBlogCommand> {
    private final BlogRepository blogRepository;

    public UnlikeBlogCommandHandler(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void handle(@NotNull UnlikeBlogCommand unlikeBlogCommand) {
        Blog blog = blogRepository.findById(unlikeBlogCommand.getBlogId());
        if (blog == null) {
            throw new IllegalArgumentException("Blog ID is invalid!");
        }
        List<Like> likes = blog.getLikes().stream()
                .filter(likeDao -> likeDao.getId().equals(unlikeBlogCommand.getLikeId())).toList();
        if (likes == null || likes.size() == 0) {
            throw new IllegalArgumentException("Like ID is invalid!");
        }
        blog.getLikes().remove(likes.get(0));
        try {
            blogRepository.addRemoveLike(blog, false);
        } catch (DataAccessException | SQLException e) {
            throw new BlogServiceException("Error occurred while the unlike: ", HttpStatus.EXPECTATION_FAILED.value());
        }
    }
}
