package com.ttice.commin.vo;

import com.ttice.entity.ArticleClass;
import lombok.Data;

import java.util.List;

@Data
public class ArticleClassPageVO {
    private List<ArticleClass> data;
    private Long total;
}
