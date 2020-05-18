package cn.wujunya.space.mapper;

import cn.wujunya.space.pojo.Role;
import cn.wujunya.space.pojo.RoleExample;
import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}