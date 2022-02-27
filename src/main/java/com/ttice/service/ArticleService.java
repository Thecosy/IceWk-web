package com.ttice.service;

import com.ttice.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ttice.vo.ArticleVO;
import com.ttice.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
public interface ArticleService extends IService<Article> {
    public PageVO VoList(Integer page, Integer limit);
}
