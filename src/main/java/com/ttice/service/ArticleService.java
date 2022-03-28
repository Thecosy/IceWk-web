package com.ttice.service;

import com.ttice.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ttice.commin.vo.ArticlePageVO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Repository
public interface ArticleService extends IService<Article> {

    ArticlePageVO VoList(Integer page, Integer limit);

    ArticlePageVO FindVoList(Integer page, Integer limit, String content);
}
