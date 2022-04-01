package com.ttice.service;

import com.ttice.commin.vo.ArticleClassPageVO;
import com.ttice.commin.vo.ResourceClassPageVO;
import com.ttice.entity.ResourceClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
public interface ResourceClassService extends IService<ResourceClass> {

    ResourceClassPageVO GetList(Integer page, Integer limit);

}
