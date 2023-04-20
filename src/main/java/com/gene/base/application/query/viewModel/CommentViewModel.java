package com.gene.base.application.query.viewModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentViewModel {
    private String id;
    private String parentCommentId;
    private String commentDesc;
    private boolean isDeleted;
    private Date createdOn;
    private Date updatedOn;
    private String userId;
}
