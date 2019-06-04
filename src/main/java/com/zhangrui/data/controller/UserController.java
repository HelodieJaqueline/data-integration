package com.zhangrui.data.controller;

import com.zhangrui.data.domain.User;
import com.zhangrui.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:11
 * @Description:
 * @Modified: By
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/user/find")
	@ResponseBody
	public User user(@RequestBody User user) {
		return userService.find(user);
	}
}
