package com.zhangrui.data.repository;

import com.zhangrui.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:10
 * @Description:
 * @Modified: By
 */
public interface UserJpaRepository extends JpaRepository<User,Long> {
	User getDistinctByPhone(String phone);
}
