package com.ttice.controller;


import com.ttice.entity.ResourceClass;
import com.ttice.mapper.ResourceClassMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
@io.swagger.annotations.Api(tags = "Web资源分类接口")
@RestController
@RequestMapping("/WebResourceClass")
public class WebResourceClassController {

    @Autowired
    private ResourceClassMapper resourceClassMapper;

    @ApiOperation(value = "获取全部资源分类列表")
    @GetMapping("/getResourceClasslist")
    public List<ResourceClass> getResourceClasslist(
    ){
        return resourceClassMapper.selectList(null);
    }

}

