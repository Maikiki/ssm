package service;

import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Froid on 2017/5/12.
 */
public interface UserService {

    User getUser(Integer userid);

    List<User> getUsers(int pageNum, int pageSize);
}
