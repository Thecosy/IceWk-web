package com.ttice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttice.entity.Article;
import com.ttice.entity.User;
import com.ttice.mapper.ArticleMapper;
import com.ttice.mapper.UserMapper;
import com.ttice.service.ArticleCommentService;
import com.ttice.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ttice.commin.vo.ArticleVO;
import com.ttice.commin.vo.ArticlePageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Override
    public ArticlePageVO FindVoList(Integer page, Integer limit , String content) {
        List<ArticleVO> result = new ArrayList<>();

        ArticleVO articleVO = null;

        Page<Article> articlePage = new Page<>(page,limit);

        QueryWrapper<Article> wrapper= new QueryWrapper<Article>();
        wrapper.orderByDesc("id") .like("title",content);

        Page<Article> resultPage = this.articleMapper.selectPage(articlePage, wrapper);

        List<Article> articles = resultPage.getRecords();
        long total = resultPage.getTotal();
        for (Article article : articles) {

            //根据作者名称查询对应的头像地址
            String author = article.getAuthor();
            User users = userMapper.searchName(author);
            String profile = users.getProfile();
            articleVO = new ArticleVO();
            articleVO.setProfile(profile);

            BeanUtils.copyProperties(article,articleVO);
            result.add(articleVO);
        }
        ArticlePageVO articlePageVO = new ArticlePageVO();
        articlePageVO.setData(result);
        articlePageVO.setTotal(total);
        return articlePageVO;
    }

    @Override
    public ArticlePageVO VoList(Integer page, Integer limit) {
        List<ArticleVO> result = new ArrayList<>();

        ArticleVO articleVO = null;

        Page<Article> articlePage = new Page<>(page,limit);

        QueryWrapper<Article> wrapper= new QueryWrapper<Article>();
        wrapper.orderByDesc("id");

        Page<Article> resultPage = this.articleMapper.selectPage(articlePage, wrapper);


        List<Article> articles = resultPage.getRecords();
        for (Article article : articles) {

            //根据作者名称查询对应的头像地址
            String author = article.getAuthor();
            User users = userMapper.searchName(author);
            String profile = users.getProfile();
            articleVO = new ArticleVO();
            articleVO.setProfile(profile);

            //根据文章id获取对应的评论数
            Integer aid = article.getId();
            int acnum = articleCommentService.GetCommentNum(aid);
            articleVO.setCommentNum(acnum);


            BeanUtils.copyProperties(article,articleVO);
            result.add(articleVO);
        }
        ArticlePageVO articlePageVO = new ArticlePageVO();
        articlePageVO.setData(result);
        articlePageVO.setTotal(resultPage.getTotal());
        return articlePageVO;
    }
}
