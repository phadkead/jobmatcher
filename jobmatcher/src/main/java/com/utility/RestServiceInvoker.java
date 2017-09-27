package com.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author aditiphadke
 *
 */
@Service
public class RestServiceInvoker {

	private final RestTemplate restTemplate;

	@Value("${api.host}")
	private String apiHost;

	public RestServiceInvoker(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public <T> ResponseEntity<T> invoke(String uri, Class<T> type) {
		return this.restTemplate.getForEntity(apiHost + uri, type);
	}

}
