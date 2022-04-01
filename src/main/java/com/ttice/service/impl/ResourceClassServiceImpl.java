package com.ttice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttice.commin.vo.ArticleClassPageVO;
import com.ttice.commin.vo.ResourceClassPageVO;
import com.ttice.entity.ResourceClass;
import com.ttice.mapper.ResourceClassMapper;
import com.ttice.service.ResourceClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
@Service
public class ResourceClassServiceImpl extends ServiceImpl<ResourceClassMapper, ResourceClass> implements ResourceClassService {

    @Autowired
    private ResourceClassMapper resourceClassMapper;

    @Override
    public ResourceClassPageVO GetList(Integer page, Integer limit) {
        Page<ResourceClass> ResourceClassPage = new Page<>(page,limit);

        QueryWrapper<ResourceClass> wrapper= new QueryWrapper<ResourceClass>();
        wrapper.orderByDesc("id");

        Page<ResourceClass> resultPage = this.resourceClassMapper.selectPage(ResourceClassPage, wrapper);
        List<ResourceClass> records = resultPage.getRecords();
        long total = resultPage.getTotal();
        ResourceClassPageVO classPageVO = new ResourceClassPageVO();
        classPageVO.setData(records);
        classPageVO.setTotal(total);
        return classPageVO;
    }
}
