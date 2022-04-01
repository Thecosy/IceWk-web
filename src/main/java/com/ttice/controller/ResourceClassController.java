package com.ttice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.commin.vo.ResourceClassPageVO;
import com.ttice.entity.ResourceClass;
import com.ttice.mapper.ResourceClassMapper;
import com.ttice.service.ResourceClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-03-28
 */
@io.swagger.annotations.Api(tags = "后台资源分类管理接口")
@RestController
@RequestMapping("/ResourceClass")
public class ResourceClassController {

    @Autowired
    private ResourceClassService resourceClassService;

    @Autowired
    private ResourceClassMapper resourceClassMapper;

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "新建资源分类")
    @ApiImplicitParam(name = "class",value = "文章分类对象",required = true)
    @PostMapping("/newResourceClass")
    public int newResourceClass(
            @RequestBody ResourceClass resourceClass
    ) {

        QueryWrapper<ResourceClass> wrapper= new QueryWrapper<ResourceClass>();
        wrapper.eq("name", resourceClass.getName());
        ResourceClass userjudje = resourceClassService.getOne(wrapper);
        if(userjudje != null){
            //该分类已存在
            return 143;
        }
        return this.resourceClassMapper.insert(resourceClass);
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "获取资源分类列表(分页)")
    @PostMapping("/allResourceClass/{page}/{limit}")
    public ResourceClassPageVO allResourceClass(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return this.resourceClassService.GetList(page,limit);
    }

    @RequiresAuthentication  //需要登陆认证的接口
    @ApiOperation(value = "删除资源分类")
    @GetMapping("/DeleteResourceClass/{id}")
    public int DeleteResourceClass(
            @PathVariable("id") Integer id
    ){
        return this.resourceClassMapper.deleteById(id);
    }
//
//    @RequiresAuthentication  //需要登陆认证的接口
//    @ApiOperation(value = "获取全部分类列表")
//    @GetMapping("/getAllClassName")
//    public List<ClassNameVO> getAllClassName(){
//        List<ClassNameVO> result = new ArrayList<>();
//
//        QueryWrapper<ArticleClass> wrapper= new QueryWrapper<ArticleClass>();
//        wrapper.select("name");
//        ClassNameVO classNameVO = null;
//        List<ArticleClass> articleClasses = resourceClassMapper.selectList(wrapper);
//        for (ArticleClass articleClass : articleClasses) {
//            classNameVO = new ClassNameVO();
//            BeanUtils.copyProperties(articleClass,classNameVO);
//            result.add(classNameVO);
//        }
//        return result;
//    }
}

