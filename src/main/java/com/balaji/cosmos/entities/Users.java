package com.balaji.cosmos.entities;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.CosmosUniqueKey;
import com.azure.spring.data.cosmos.core.mapping.CosmosUniqueKeyPolicy;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Container(containerName = "users")
@CosmosUniqueKeyPolicy(uniqueKeys = { @CosmosUniqueKey(paths = { "/email" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	@Id
	private String id;
	@PartitionKey
	private String role;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String loginId;
}
