package com.wdw.maj.mapper;


import com.wdw.maj.model.User;
import org.apache.ibatis.annotations.*;

/**
 * 当形参是一个类时，可以直接如insert那样注入
 * 当不是一个类时，需要加入@param
 *
 * 没有实现类来实现该接口，可以添加@Mapper注解，spring会自动创建dao实现类并注入bean容器
 * 注意：需要导入相应依赖 mybatis-spring-boot-starter
 */

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatarUrl) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token")String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatarUrl = #{avatarUrl} where id = #{id}")
    void update(User doUser);

    @Select("select * from user where account_Id = #{accountId}")
    User findByAccountid(@Param("accountId") String accountId);
}
