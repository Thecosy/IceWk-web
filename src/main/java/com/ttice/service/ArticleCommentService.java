package com.ttice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ttice.entity.ArticleComment;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentService extends IService<ArticleComment> {
    int GetCommentNum(Integer articleId);

}
