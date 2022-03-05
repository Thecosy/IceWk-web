package com.ttice.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.entity.DispositionCarousel;
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
public interface DispositionCarouselMapper extends BaseMapper<DispositionCarousel> {
    @Select("select * from disposition_carousel  order by id")
    List<DispositionCarousel> selectAll();
}
