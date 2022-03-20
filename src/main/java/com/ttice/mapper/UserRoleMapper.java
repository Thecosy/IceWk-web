package com.ttice.mapper;

import com.ttice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttice.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-01-13
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
