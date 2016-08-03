package com.metafour.apikeyauthdemo.security;

import java.util.TreeSet;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.metafour.apikeyauthdemo.entity.ApiKey;

public class ApiKeyUtils {
	public static synchronized ApiKey getPrincipal() {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (u == null || u.getUsername() == null || u.getUsername().isEmpty()) return null;
		
		ApiKey apiKey = new ApiKey();
		
		apiKey.setKey(u.getUsername());
		apiKey.setEnabled(u.isEnabled());
		apiKey.setExpired(!u.isAccountNonExpired());
		apiKey.setLocked(!u.isAccountNonLocked());
		apiKey.setAuthorities(new TreeSet<>());
		u.getAuthorities().stream().map(a -> a.getAuthority()).forEach(apiKey.getAuthorities()::add);
		
		return apiKey;
	}
}
