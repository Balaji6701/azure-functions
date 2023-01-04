package com.balaji.cosmos.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public abstract class Auditable<T> {

	@CreatedDate
	protected LocalDateTime createdAt;
	@CreatedBy
	protected T createdBy;

	@LastModifiedDate
	protected LocalDateTime lastModifiedAt;
	@LastModifiedBy
	protected T lastModifiedBy;
}
