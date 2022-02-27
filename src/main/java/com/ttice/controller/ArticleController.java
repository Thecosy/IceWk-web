package com.ttice.controller;


import com.ttice.Util.MathUtils;
import com.ttice.entity.Article;
import com.ttice.mapper.ArticleMapper;
import com.ttice.service.ArticleService;
import com.ttice.vo.ArticleStatusVO;
import com.ttice.vo.PageVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    @ApiOperation(value = "新增文章")
    @ApiImplicitParam(name = "article",value = "文章",required = true)
    @PostMapping("/create")
    public ArticleStatusVO add(@RequestBody Article article) throws ParseException {
       //判断是不是新文章
        if(article.getArticleStatus()==null){
            System.out.println("新文章");
            //对新文章进行保存

            //生成随机数注入
            int number = MathUtils.randomDigitNumber(7);
            article.setArticleStatus(number);
            int insert = articleMapper.insert(article);

            Integer articleId = article.getId();
            Integer ArticleStatus = article.getArticleStatus();
            ArticleStatusVO articleStatusVO = new ArticleStatusVO();
            articleStatusVO.setArticleStatus(ArticleStatus);
            articleStatusVO.setId(articleId);
            return articleStatusVO;
        }
        else{
            System.out.println("不是新文章");
            //不是新文章，但是没有id！！！！
            boolean ReviseArticleById = ReviseArticleById(article);
            return null;
        }
    }

    @ApiOperation(value = "根据id删除文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/DelectArticleById/{id}")
    public boolean DelectArticleById(
            @PathVariable("id") Integer id
    ) {
        return this.articleService.removeById(id);
    }

    @ApiOperation(value = "根据id修改文章")
    @ApiImplicitParam(name = "id",value = "文章对象",required = true)
    @PostMapping("/ReviseArticleById/{id}")
    public boolean ReviseArticleById(
            @RequestBody Article article
    ) {
        return this.articleService.updateById(article);
    }

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

}

