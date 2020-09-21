package com.citiesconnected.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CityProcessor {

	private final Log logger = LogFactory.getLog(getClass());

	private Map<String, City> cityMap = new HashMap<>();

	@Value("${data.file:classpath:city.txt}")
	private String cities;

	@Autowired
	private ResourceLoader resourceLoader;

	public Map<String, City> getCityMap() {
		return cityMap;
	}

	@PostConstruct
	private void read() throws IOException {

		logger.info("loading resource from cities.txt");

		Resource resource = resourceLoader.getResource(cities);

		InputStream is = resource.getInputStream();

		Scanner scanner = new Scanner(is);

		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line))
				continue;

			logger.info(line);

			String[] split = line.split(",");
			String sourceCity = split[0].trim().toUpperCase();
			String destinationCity = split[1].trim().toUpperCase();

			if (!sourceCity.equals(destinationCity)) {
				City source = cityMap.getOrDefault(sourceCity, City.build(sourceCity));
				City destination = cityMap.getOrDefault(destinationCity, City.build(destinationCity));

				source.connectedCities(destination);
				destination.connectedCities(source);

				cityMap.put(source.getName(), source);
				cityMap.put(destination.getName(), destination);
			}
		}

		logger.info("Map: " + cityMap);

		scanner.close();

	}

	public City getCity(String name) {
		return cityMap.get(name);
	}

}
