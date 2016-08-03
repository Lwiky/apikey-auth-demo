package com.metafour.apikeyauthdemo.entity;

import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.metafour.apikeyauthdemo.entity.converter.StringSetToCsvConverter;

@Entity
public class ApiPattern {
	@Id
	private String pattern;
	@Convert(converter = StringSetToCsvConverter.class)
	private Set<String> authorities;
	
	public ApiPattern() { }

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "ApiPattern [pattern=" + pattern + ", authorities=" + authorities + "]";
	}

	
	
}
