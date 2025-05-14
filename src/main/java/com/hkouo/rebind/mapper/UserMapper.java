package com.hkouo.rebind.mapper;

import com.hkouo.rebind.model.ScenarioCharacter;
import com.hkouo.rebind.model.User;
import com.hkouo.rebind.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    int isUserIdDuplicate(String userId);
    void insertUser(User user);

    void insertUserRole(Map<String, Object> param);
    void insertScenarioCharacters(List<ScenarioCharacter> characters);


}



