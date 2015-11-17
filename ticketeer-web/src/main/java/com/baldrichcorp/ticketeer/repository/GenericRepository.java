package com.baldrichcorp.ticketeer.repository;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public interface GenericRepository<I extends Serializable, E extends Serializable> {
	
	@NotNull
	Collection<E> getAll();
	
	E get(@NotNull I id);
	
	void add(@NotNull E entity);
	
	void update(@NotNull E entity);
	
	void delete(@NotNull E entity);
	
	void deleteById(@NotNull I id);
}
