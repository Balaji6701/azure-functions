package com.balaji.cosmos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.core.annotation.PathParam;
import com.balaji.cosmos.entities.Users;
import com.balaji.cosmos.exceptions.UserNotFoundException;
import com.balaji.cosmos.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@Slf4j
public class UserController {
	
	private UserService userService;
	
	@GetMapping
	public List<Users> getUsers(){
		log.info("Fetching all users");
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public Users getUserById(@PathParam("id")String id) throws UserNotFoundException{
		log.info("Fetching user with id: {}", id);
		return userService.getUser(id);
	}
	
	@PostMapping
	public Users saveUser(@RequestBody Users users){
		log.info("Saving user: {}", users);
		return userService.saveUser(users);
	}
}
