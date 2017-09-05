package service.impl;

import dao.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

/**
 * Created by Froid on 2017/7/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    public User getUser(Integer userid) {
        return null;
    }

    public List<User> getUsers(int pageNum, int pageSize) {
        return null;
    }
}
