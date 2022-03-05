package com.ttice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-01-13
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer userId;

    private Integer userAge;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    private String token;

    private String profile;

    private Integer status;

    private Date created;

  private Date lastLogin;

}
