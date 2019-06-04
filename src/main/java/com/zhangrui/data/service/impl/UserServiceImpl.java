package com.zhangrui.data.service.impl;

import com.zhangrui.data.domain.User;
import com.zhangrui.data.repository.IUserRepository;
import com.zhangrui.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:16
 * @Description:
 * @Modified: By
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User find(User user) {
		return userRepository.getByPhone(user.getPhone());
	}
}
