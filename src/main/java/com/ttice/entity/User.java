package com.ttice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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

    private String userId;

    private Integer userAge;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    private String token;

    private String profile;

}
