package com.ttice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.entity.Article;
import com.ttice.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleVOMapper extends BaseMapper<Article> {

    @Select("select * from article  order by id desc limit ${nameCn}")
    List<ArticleVO> selectAll(@Param("nameCn") Integer nameCn);
}