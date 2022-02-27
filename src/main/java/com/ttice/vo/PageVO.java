package com.ttice.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页VO
 */

@Data
public class PageVO {
    private List<ArticleVO> data;
    private Long total;
}
