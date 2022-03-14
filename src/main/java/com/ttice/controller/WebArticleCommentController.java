package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.entity.Article;
import com.ttice.entity.ArticleComment;
import com.ttice.mapper.ArticleCommentMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin
@io.swagger.annotations.Api(tags = "Web文章评论管理接口")
@RestController
@RequestMapping("/WebArticleClass")
public class WebArticleCommentController {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @ApiOperation(value = "根据文章id查询对应的评论")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getallArticleComment/{articleId}")
    public List<ArticleComment> getallArticleComment(
            @PathVariable("articleId") Integer articleId
    ) {

        QueryWrapper<ArticleComment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",articleId);

        return articleCommentMapper.selectList(wrapper);
    }

    @ApiOperation(value = "获取全部评论")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getallArticleComments")
    public List<ArticleComment> getallArticleComments(
    ) {
        QueryWrapper<ArticleComment> wrapper = new QueryWrapper<>();

        return null;
    }

    @ApiOperation(value = "增加评论")
    @PostMapping("/addArticleComment")
    @ApiImplicitParam(name = "articleComment",value = "文章分类对象",required = true)
    public int addArticleComment(@RequestBody ArticleComment articleComment) {
        return articleCommentMapper.insert(articleComment);
    }

    @ApiOperation(value = "查看文章对应评论数")
    @GetMapping("/getArticleCommentnum/{articleId}")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    public int getArticleCommentnum(
            @PathVariable("articleId") Integer articleId
    ) {
        QueryWrapper<ArticleComment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",articleId);
        return articleCommentMapper.selectCount(wrapper);
    }

}

