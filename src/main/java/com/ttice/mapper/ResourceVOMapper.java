package com.ttice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.commin.vo.ArticleVO;
import com.ttice.commin.vo.ResourceVO;
import com.ttice.entity.Article;
import com.ttice.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceVOMapper extends BaseMapper<Resource> {

    @Select("select * from resource  order by id desc limit ${nameCn}")
    List<ResourceVO> selectAll(@Param("nameCn") Integer nameCn);
}