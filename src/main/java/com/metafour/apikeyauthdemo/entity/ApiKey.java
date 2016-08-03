package com.metafour.apikeyauthdemo.entity;

import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.metafour.apikeyauthdemo.entity.converter.StringSetToCsvConverter;

@Entity
public class ApiKey {
	@Id
	private String key;
	private Boolean expired;
	private Boolean locked;
	private Boolean enabled;
	@Convert(converter = StringSetToCsvConverter.class)
	private Set<String> authorities;
	
	public ApiKey() {
		super();
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Boolean isExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	public Boolean isLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Set<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "ApiKey [key=" + key + ", expired=" + expired + ", locked=" + locked + ", enabled=" + enabled
				+ ", authorities=" + authorities + "]";
	}
		
	
}
