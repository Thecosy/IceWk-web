package com.ttice.service;

import com.ttice.commin.vo.ResourcePageVO;
import com.ttice.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
public interface ResourceService extends IService<Resource> {

    ResourcePageVO VoList(Integer page, Integer limit);
}
