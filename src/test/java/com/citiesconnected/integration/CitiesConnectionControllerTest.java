package com.citiesconnected.integration;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.citiesconnected.model.CityProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CitiesConnectionControllerTest {

	@Autowired
	CityProcessor cityProcessor;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void fileLoad() {
		Assert.assertFalse("File load failed", cityProcessor.getCityMap().isEmpty());
	}

	@Test
	public void testTwoCitiesConnectedWhenGivenValidSourceAndDestination() {

		Map<String, String> cities = new HashMap<>();
		cities.put("origin", "Boston");
		cities.put("destination", "Newark");
		String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				cities);
		Assert.assertEquals("yes", body);
	}

	@Test
	public void testTwoCitiesConnectedWhenGivenInValidSourceAndDestination() {

		Map<String, String> cities = new HashMap<>();
		cities.put("origin", "Boston");
		cities.put("destination", "Albany");

		String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				cities);
		Assert.assertEquals("no", body);
	}

	@Test
	public void testTwoCitiesGetInfo() {
		String body = restTemplate.getForObject("/info", String.class);
		assertNotNull(body);
	}

	@Test
	public void testTwoCitiesConnectedWhenBadRequest() {
		ResponseEntity<String> response = restTemplate.exchange("/connected?origin=Bostn&destination=Newark",
				HttpMethod.GET, HttpEntity.EMPTY, String.class);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

}
