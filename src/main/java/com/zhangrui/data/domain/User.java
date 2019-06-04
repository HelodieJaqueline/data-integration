package com.zhangrui.data.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-16:52
 * @Description:
 * @Modified: By
 */
@Data
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String phone;

	private String name;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
