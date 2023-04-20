package com.gene.base.infrastructure.repository;

import com.gene.base.infrastructure.entity.BlogDao;
import com.gene.base.infrastructure.entity.CommentDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMysqlBlogRepository extends JpaRepository<BlogDao, String> {
    @Query(value = "SELECT * FROM blog_dao b WHERE b.user_id=?1 AND b.is_deleted=false", nativeQuery = true)
    Page<BlogDao> findByUserId(String userId, Pageable pageable);

    CommentDao findByCommentDaos_Id(String commentId);

}
