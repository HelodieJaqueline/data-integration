package com.zhangrui.data.repository;

import com.zhangrui.data.domain.User;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:32
 * @Description:
 * @Modified: By
 */
public interface IUserRepository {

	User getByPhone(String phone);
}
