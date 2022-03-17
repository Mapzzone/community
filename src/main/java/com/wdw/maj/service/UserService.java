package com.wdw.maj.service;


import com.wdw.maj.mapper.UserMapper;
import com.wdw.maj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User doUser = userMapper.findByAccountid(user.getAccountId());
        if (doUser == null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            doUser.setGmtModified(user.getGmtCreate());
            doUser.setAvatarUrl(user.getAvatarUrl());
            doUser.setToken(user.getToken());
            doUser.setName(user.getName());
            userMapper.update(doUser);
        }
    }
}
