package com.ttice.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.ttice.entity.ArticleClass;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;
        import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Repository
public interface ArticleClassMapper extends BaseMapper<ArticleClass> {

}

