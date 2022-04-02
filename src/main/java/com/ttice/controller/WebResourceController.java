package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.commin.vo.ResourcePageVO;
import com.ttice.commin.vo.ResourceVO;
import com.ttice.entity.Resource;
import com.ttice.mapper.ResourceMapper;
import com.ttice.mapper.ResourceVOMapper;
import com.ttice.service.ResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
@io.swagger.annotations.Api(tags = "Web资源接口")
@RestController
@RequestMapping("/WebResource")
public class WebResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceVOMapper resourceVOMapper;

    @ApiOperation(value = "根据id获取资源内容")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/getResourceById/{id}")
    public Resource getResourceById(
            @PathVariable("id") Integer id
    ) {
        return this.resourceService.getById(id);
    }

    @ApiOperation(value = "获取全部资源列表(分页)")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getAllResource/{page}/{limit}")
    public ResourcePageVO getAllArticle(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return this.resourceService.VoList(page, limit);
    }

    @ApiOperation(value = "获取所有资源数量")
    @GetMapping("/getAllResourceNumber")
    public Integer getAllResourceNumber() {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.select().eq("status","published");
        return resourceMapper.selectCount(wrapper);
    }

    @ApiOperation(value = "获取最新资源列表")
    @ApiImplicitParam(name = "articleNum",value = "文章数量",required = true)
    @GetMapping("/getNewArticle/{articleNum}")
    public List<ResourceVO> getNewAllArticle(
            @PathVariable("articleNum") Integer articleNum
    ) {

        return resourceVOMapper.selectAll(articleNum);
    }

}

