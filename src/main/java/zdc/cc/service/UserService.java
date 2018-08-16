package zdc.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zdc.cc.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


}
