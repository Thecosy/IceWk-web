package com.ttice.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ttice.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.vo.ArticleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
