package com.ttice.commin.vo;

import lombok.Data;

import java.util.Date;

/**
 * 后台文章列表
 */

@Data
public class ArticleVO {
    private Integer id;
    private Date addTime;
    private Date createTime;
    private String author;
    private String title;
    private Integer ownerTag;
    private String status;
    private String thumb;

    private String profile;
}
