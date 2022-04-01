package com.ttice.commin.vo;

import com.ttice.entity.ArticleClass;
import com.ttice.entity.ResourceClass;
import lombok.Data;

import java.util.List;

@Data
public class ResourceClassPageVO {
    private List<ResourceClass> data;
    private Long total;
}
