package com.liam.javareactmysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.liam.javareactmysql.services.UserService;

@RestController
public class MainController {
	
	@Autowired
	private UserService userServ;

}
