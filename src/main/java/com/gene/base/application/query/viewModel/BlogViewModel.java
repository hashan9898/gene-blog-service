package com.gene.base.application.query.viewModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BlogViewModel {
    private static final String BLOG_ID_PREFIX = "bid-";
    private String id;
    private String userId;
    private String content;
    private String title;
    private String description;
    private String location;
    private Date createdOn;
    private Date updatedOn;
    private List<CommentViewModel> commentViewModels;
    private List<LikeViewModel> likeViewModels;
    private boolean isDeleted;
}
