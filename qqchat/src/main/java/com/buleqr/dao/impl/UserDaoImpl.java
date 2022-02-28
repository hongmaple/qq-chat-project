package com.buleqr.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buleqr.dao.UserDao;
import com.buleqr.mapper.UserMapper;
import com.buleqr.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author buleqr
 */
@Repository
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
