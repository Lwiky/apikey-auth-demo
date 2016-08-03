package com.metafour.apikeyauthdemo.security;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class ApiKeyHeaderFilter extends OncePerRequestFilter {
	private String apiKeyHeader;
	
	public ApiKeyHeaderFilter(String apiKeyHeader) {
		this.apiKeyHeader = apiKeyHeader;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				
		if (request.getHeader(apiKeyHeader) == null) { // API key is sent as basic auth username
			filterChain.doFilter(request, response);
		} else { // API key is sent as header
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
		        @Override
		        public String getHeader(String name) {
		        	String auth = Base64.getEncoder().encodeToString((request.getHeader(apiKeyHeader) + ":").getBytes());
		        	
		        	if ("Authorization".equalsIgnoreCase(name)) return "Basic " + auth;
		        	else if (name.equalsIgnoreCase(apiKeyHeader)) return null;
		        	
		            final String value = request.getParameter(name);
		            if (value != null) return value;
		            
		            return super.getHeader(name);
		        }
		    };
		    filterChain.doFilter(wrapper, response);
		}
	}

}
