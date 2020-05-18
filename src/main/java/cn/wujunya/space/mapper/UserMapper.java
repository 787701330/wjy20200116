package cn.wujunya.space.mapper;

import cn.wujunya.space.pojo.User;
import cn.wujunya.space.pojo.UserExample;
import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}