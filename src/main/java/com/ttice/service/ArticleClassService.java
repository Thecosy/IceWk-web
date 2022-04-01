package com.ttice.service;

        import com.ttice.commin.vo.ArticleClassPageVO;
        import com.baomidou.mybatisplus.extension.service.IService;
        import com.ttice.entity.ArticleClass;
        import org.springframework.stereotype.Repository;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Repository
public interface ArticleClassService extends IService<ArticleClass> {

    ArticleClassPageVO GetList(Integer page, Integer limit);

}
