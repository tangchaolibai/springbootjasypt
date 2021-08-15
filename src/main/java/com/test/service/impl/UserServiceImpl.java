package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.UserMapper;
import com.test.model.User;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper userMapper;
	
	@Override
	public List<User> findAllUser() {
		List<User> selectList = userMapper.selectList(null);
		return selectList;
	}

}
