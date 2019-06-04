package com.zhangrui.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-16:02
 * @Description:
 * @Modified: By
 */
@RestController
public class TestController {

	@GetMapping(value = "/test")
	public String test(){
		return "Hello!";
	}

}
