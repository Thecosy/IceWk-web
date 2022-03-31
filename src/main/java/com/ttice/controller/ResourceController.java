package com.ttice.controller;


import com.ttice.commin.vo.ResourcePageVO;
import com.ttice.service.ResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
@io.swagger.annotations.Api(tags = "后台资源管理接口")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "获取全部资源(分页)")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @GetMapping("/getAllResource/{page}/{limit}")
    public ResourcePageVO getAllResource(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return this.resourceService.VoList(page,limit);
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "根据id删除资源")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/DelectResourceById/{id}")
    public boolean DelectResourceById(
            @PathVariable("id") Integer id
    ) {
        return this.resourceService.removeById(id);
    }
}

