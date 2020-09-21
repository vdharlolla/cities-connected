package com.citiesconnected;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.citiesconnected.CitiesConnectionApplication;

@RunWith(SpringRunner.class)
class CitiesConnectionApplicationTest {

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		CitiesConnectionApplication.main(new String[] {});
		assertNotNull(CitiesConnectionApplication.class);
	}

}
