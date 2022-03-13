package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.Util.MathUtils;
import com.ttice.commin.vo.ClassNameVO;
import com.ttice.commin.vo.ClassPageVO;
import com.ttice.commin.vo.PageVO;
import com.ttice.entity.Article;
import com.ttice.entity.ArticleClass;
import com.ttice.mapper.ArticleClassMapper;
import com.ttice.mapper.ArticleMapper;
import com.ttice.service.ArticleClassService;
import com.ttice.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@io.swagger.annotations.Api(tags = "后台文章管理接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleClassService articleClassService;

    @Autowired
    private ArticleClassMapper articleClassMapper;

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "新增文章")
    @ApiImplicitParam(name = "article",value = "文章",required = true)
    @PostMapping("/create")
    public Integer add(@RequestBody Article article) throws ParseException {
        //生成随机数注入
        int number = MathUtils.randomDigitNumber(7);
        article.setArticleStatus(number);

        //saveOrUpdate:要在插入数据库时，如果有某一个主要字段的值重复，则不插入，否则则插入！
        articleService.saveOrUpdate(article);
        return article.getId();
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "根据id删除文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/DelectArticleById/{id}")
    public boolean DelectArticleById(
            @PathVariable("id") Integer id
    ) {
        return this.articleService.removeById(id);
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "根据id修改文章")
    @ApiImplicitParam(name = "id",value = "文章对象",required = true)
    @PostMapping("/ReviseArticleById/{id}")
    public boolean ReviseArticleById(
            @RequestBody Article article
    ) {
        return this.articleService.updateById(article);
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "根据id获取文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/getArticleById/{id}")
    public Article getArticleById(
            @PathVariable("id") Integer id
    ) {
        return this.articleService.getById(id);
    }

    @ApiOperation(value = "获取全部文章(分页)")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getAllArticle/{page}/{limit}")
    public PageVO getAllArticle(
        @PathVariable("page") Integer page,
        @PathVariable("limit") Integer limit
    ) {
        return this.articleService.VoList(page,limit);
    }

    @ApiOperation(value = "新建文章分类")
    @ApiImplicitParam(name = "class",value = "文章分类对象",required = true)
    @PostMapping("/newArticleClass")
    public int newArticleClass(
            @RequestBody ArticleClass articleClass
    ) {
        return this.articleClassMapper.insert(articleClass);
    }

    @ApiOperation(value = "获取文章分类列表(分页)")
    @PostMapping("/allArticleClass/{page}/{limit}")
    public ClassPageVO allArticleClass(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return this.articleClassService.GetList(page,limit);
    }

    @ApiOperation(value = "删除文章分类")
    @GetMapping("/DeleteArticleClass/{id}")
    public int DeleteArticleClass(
            @PathVariable("id") Integer id
    ){
        return this.articleClassMapper.deleteById(id);
    }

    @ApiOperation(value = "获取全部分类列表")
    @GetMapping("/getAllClassName")
    public List<ClassNameVO> getAllClassName(){
        List<ClassNameVO> result = new ArrayList<>();

        QueryWrapper<ArticleClass> wrapper= new QueryWrapper<ArticleClass>();
        wrapper.select("name");
        ClassNameVO classNameVO = null;
        List<ArticleClass> articleClasses = articleClassMapper.selectList(wrapper);
        for (ArticleClass articleClass : articleClasses) {
            classNameVO = new ClassNameVO();
            BeanUtils.copyProperties(articleClass,classNameVO);
            result.add(classNameVO);
        }
        return result;
    }
}

