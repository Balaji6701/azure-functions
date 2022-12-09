package com.balaji.cosmos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.balaji.cosmos.entities.Users;
import com.balaji.cosmos.exceptions.UserNotFoundException;
import com.balaji.cosmos.repositories.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UsersRepository usersRepository;
	
	public List<Users> getUsers(){
		List<Users> users = new ArrayList<>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Users getUser(String id) throws UserNotFoundException {
		Optional<Users> optionalUser = usersRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			throw new UserNotFoundException("User with id: "+ id+" not found");
		}
	}
	
	public Users saveUser(Users user) {
		return usersRepository.save(user);
	}
}
