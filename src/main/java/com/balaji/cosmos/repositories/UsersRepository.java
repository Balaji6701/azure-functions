package com.balaji.cosmos.repositories;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.balaji.cosmos.entities.Users;

@Repository
public interface UsersRepository extends CosmosRepository<Users, String>{

}
