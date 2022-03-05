package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.entity.Article;
import com.ttice.mapper.ArticleMapper;
import com.ttice.mapper.ArticleVOMapper;
import com.ttice.service.ArticleService;
import com.ttice.commin.vo.ArticleVO;
import com.ttice.commin.vo.PageVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@io.swagger.annotations.Api(tags = "Web文章管理接口")
@RestController
@RequestMapping("/WebArticle")
public class WebArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleVOMapper articleVOMapper;

    @ApiOperation(value = "根据id获取文章内容")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/getArticleById/{id}")
    public Article getArticleById(
            @PathVariable("id") Integer id
    ) {
        return this.articleService.getById(id);
    }

    @ApiOperation(value = "获取全部文章列表(分页)")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getAllArticle/{page}/{limit}")
    public PageVO getAllArticle(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return this.articleService.VoList(page, limit);
    }

    @ApiOperation(value = "获取最新文章列表")
    @ApiImplicitParam(name = "articleNum",value = "文章数量",required = true)
    @GetMapping("/getNewArticle/{articleNum}")
    public List<ArticleVO> getNewAllArticle(
            @PathVariable("articleNum") Integer articleNum
    ) {

        return articleVOMapper.selectAll(articleNum);
    }

    @ApiOperation(value = "获取所有文章数量")
    @GetMapping("/getAllArticleNumber")
    public Integer getAllArticleNumber() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select().eq("status","published");
        return articleVOMapper.selectCount(wrapper);
    }

    @ApiOperation(value = "统计文章浏览量+1")
    @GetMapping("/articles/{id}/view")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    public Boolean articlesBrowse(
        @PathVariable("id") Integer id
    ) {
        return articleMapper.articlesBrowse(id);
    }

    @ApiOperation(value = "文章查询")
    @GetMapping("/findarticles/{content}")
    @ApiImplicitParam(name = "content",value = "模糊查询标题",required = true)
    public List<Article> Findarticles(
            @PathVariable("content") String content
    ) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        //用户名判断
        wrapper.like("title",content);
        return articleMapper.selectList(wrapper);
    }
}

