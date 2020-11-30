package com.security.mapper;

import com.security.domain.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MyUserDetailsMapper {

    // 根据用户名查询用户的信息
    @Select("select username,`password`,enabled,accountNonLocked\n" +
            "from sys_user where username = #{userId}")
    MyUserDetails findByUserName(@Param("userId") String userId);

    // 根据用户的id来查询用户所具有的角色信息
    @Select(
           "select role_code\n" +
                   "from sys_role r\n" +
                   "left join sys_user_role ur on r.id = ur.role_id\n" +
                   "left join sys_user u on u.id  = ur.user_id\n" +
                   "where u.username = #{userId}"
       )
    List<String> findRoleByUserName(@Param("userId") String userId);

    // 根据角色信息来查看用户的访问权限
    @Select({
            "<script>",
            "SELECT url " ,
            "FROM sys_menu m " ,
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " ,
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    // 更新用户的的锁定的状态
    @Update({"UPDATE sys_user u \n" +
            " SET u.accountNonLocked = 0 \n" +
            " WHERE u.username = #{userId}" })
    int updateLockedByUserId(@Param("userId")  String userId);
}
