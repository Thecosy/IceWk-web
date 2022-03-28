package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.entity.Article;
import com.ttice.entity.ArticleClass;
import com.ttice.mapper.ArticleClassMapper;
import com.ttice.mapper.ArticleMapper;
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
@io.swagger.annotations.Api(tags = "Web文章分类接口")
@RestController
@RequestMapping("/WebarticleClass")
public class WebArticleClassController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleClassMapper articleClassMapper;

    @ApiOperation(value = "根据分类id获取文章数量")
    @GetMapping("/getArticleClassNum/{id}")
    public int getArticleClassNum(
            @PathVariable("id") Integer id
    ){
        QueryWrapper<Article> wrapper= new QueryWrapper<Article>();
        wrapper.eq("sort_class", id);
        return articleMapper.selectCount(wrapper);
    }

    @ApiOperation(value = "获取全部分类列表")
    @GetMapping("/getArticleClasslist")
    public List<ArticleClass> getArticleClasslist(
    ){
        return articleClassMapper.selectList(null);
    }

}

