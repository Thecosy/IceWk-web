package com.ttice.service;

import com.ttice.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ttice.commin.vo.PageVO;

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
