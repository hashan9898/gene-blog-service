package com.gene.base.infrastructure.adapter.domain;

import com.gene.base.application.query.viewModel.BlogViewModel;
import com.gene.base.application.query.viewModel.CommentViewModel;
import com.gene.base.application.query.viewModel.LikeViewModel;
import com.gene.base.domain.aggregrate.blogAggregrate.Blog;
import com.gene.base.domain.aggregrate.blogAggregrate.Comment;
import com.gene.base.domain.aggregrate.blogAggregrate.Like;
import com.gene.base.infrastructure.entity.BlogDao;
import com.gene.base.infrastructure.entity.CommentDao;
import com.gene.base.infrastructure.entity.LikeDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectMapper {
    private final ModelMapper modelMapper;

    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Like> likeDaoListToLikeList(List<LikeDao> likeDaos) {
        List<Like> likes = new ArrayList<>();
        likeDaos.stream().forEach(likeDao -> {
            likes.add(modelMapper.map(likeDao, Like.class));
        });
        return likes;
    }

    public List<Comment> commentDaoListToCommentList(List<CommentDao> commentDaos) {
        List<Comment> comments = new ArrayList<>();
        commentDaos.stream().forEach(commentDao -> {
            comments.add(modelMapper.map(commentDao, Comment.class));
        });
        return comments;
    }

    public List<LikeDao> likeListToLikeDaoList(List<Like> likes) {
        List<LikeDao> likeDaos = new ArrayList<>();
        likes.stream().forEach(like -> {
            likeDaos.add(modelMapper.map(like, LikeDao.class));
        });
        return likeDaos;
    }

    public List<CommentDao> commentListToCommentDaoList(List<Comment> comments) {
        List<CommentDao> commentDaos = new ArrayList<>();
        comments.stream().forEach(comment -> {
            commentDaos.add(modelMapper.map(comment, CommentDao.class));
        });
        return commentDaos;
    }

    public Blog blogDaoToBlog(BlogDao blogDao) {
        return modelMapper.map(blogDao, Blog.class);
    }

    public Comment commentDaoToComment(CommentDao commentDao) {
        return modelMapper.map(commentDao, Comment.class);
    }

    public BlogDao blogToBlogDao(Blog blog) {
        return modelMapper.map(blog, BlogDao.class);
    }

    public CommentDao commentToCommentDao(Comment comment) {
        return modelMapper.map(comment, CommentDao.class);
    }

    public LikeDao likeToLikeDao(Like like) {
        return modelMapper.map(like, LikeDao.class);
    }

    public List<CommentViewModel> commentDaoListToCommentViewModel(List<CommentDao> commentDaos) {
        List<CommentViewModel>  commentViewModels = new ArrayList<>();
        commentDaos.stream().forEach(commentDao -> {
            commentViewModels.add(modelMapper.map(commentDao, CommentViewModel.class));
        });
        return commentViewModels;
    }

    public List<LikeViewModel> likeDaoListToLikeViewModel(List<LikeDao> likeDaos) {
        List<LikeViewModel>  likeViewModels = new ArrayList<>();
        likeDaos.stream().forEach(commentDao -> {
            likeViewModels.add(modelMapper.map(commentDao, LikeViewModel.class));
        });
        return likeViewModels;
    }

    public BlogViewModel blogDaoToBlogViewModel(BlogDao blogDao) {
        return modelMapper.map(blogDao, BlogViewModel.class);
    }
}
