package com.ttice.mapper;

import com.ttice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-01-13
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where USERNAME='${nameCn}' limit 1")
    User searchName(@Param("nameCn") String nameCn);
}
