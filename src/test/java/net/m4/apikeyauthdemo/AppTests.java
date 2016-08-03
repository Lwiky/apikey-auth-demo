package net.m4.apikeyauthdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.metafour.apikeyauthdemo.App;
import com.metafour.apikeyauthdemo.entity.ApiKey;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class AppTests {
	private static Logger logger = LoggerFactory.getLogger(AppTests.class);

	private TestRestTemplate restTemplate;
	
	private String baseUrl;
	
	@Value("${server.port}")
	private String port;
	
	@Before
	public void setUp() throws Exception {
		baseUrl = "http://localhost:" + port + "/";
		restTemplate = new TestRestTemplate();
	}
	
	
	
	@Value("${apikeyauth.header}")
	private String apiKeyHeader;
	
	private static final String VALID_API_KEY = "nadim";
	private static final String INVALID_API_KEY = "xyz";
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testValid() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(apiKeyHeader, VALID_API_KEY);
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<ApiKey> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, ApiKey.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		logger.info(response.getBody().toString());
	}
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testInvalid() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(apiKeyHeader, INVALID_API_KEY);
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<ApiKey> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, ApiKey.class);
		
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}
