package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;

@RestController
public class ConfigController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUser")
    public List<User> getUser() {
		return userService.findAllUser();
    }
	
}
