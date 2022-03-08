package com.ttice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttice.commin.vo.ClassPageVO;
import com.ttice.entity.Article;
import com.ttice.entity.ArticleClass;
import com.ttice.mapper.ArticleClassMapper;
import com.ttice.service.ArticleClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 *  ArticleClass实现分页功能
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Service
public class ArticleClassServiceImpl extends ServiceImpl<ArticleClassMapper,ArticleClass> implements ArticleClassService {

    @Autowired
    private ArticleClassMapper articleClassMapper;

    @Override
    public ClassPageVO GetList(Integer page, Integer limit) {
        Page<ArticleClass> ArticleClassPage = new Page<>(page,limit);

        QueryWrapper<ArticleClass> wrapper= new QueryWrapper<ArticleClass>();
        wrapper.orderByDesc("id");

        Page<ArticleClass> resultPage = this.articleClassMapper.selectPage(ArticleClassPage, wrapper);
        List<ArticleClass> records = resultPage.getRecords();
        long total = resultPage.getTotal();
        ClassPageVO classPageVO = new ClassPageVO();
        classPageVO.setData(records);
        classPageVO.setTotal(total);
        return classPageVO;
    }
}
