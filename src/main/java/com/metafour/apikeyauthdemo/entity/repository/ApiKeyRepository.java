package com.metafour.apikeyauthdemo.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.metafour.apikeyauthdemo.entity.ApiKey;

public interface ApiKeyRepository extends CrudRepository<ApiKey, String>{
	ApiKey findByKey(String key);
}
