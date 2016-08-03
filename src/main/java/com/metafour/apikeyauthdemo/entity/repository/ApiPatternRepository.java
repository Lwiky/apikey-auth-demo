package com.metafour.apikeyauthdemo.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.metafour.apikeyauthdemo.entity.ApiPattern;

public interface ApiPatternRepository extends CrudRepository<ApiPattern, String>{
	List<ApiPattern> findAll();
}
