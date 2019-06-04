package com.zhangrui.data.repository.impl;

import com.zhangrui.data.domain.User;
import com.zhangrui.data.repository.IUserRepository;
import com.zhangrui.data.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:30
 * @Description:
 * @Modified: By
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {
	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	@Cacheable(value = "user",key = "#phone")
	public User getByPhone(String phone) {
		return userJpaRepository.getDistinctByPhone(phone);
	}
}
