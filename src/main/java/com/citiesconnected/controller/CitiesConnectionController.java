package com.citiesconnected.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiesconnected.exception.CitiesConnectionValidationException;
import com.citiesconnected.model.City;
import com.citiesconnected.model.CityProcessor;
import com.citiesconnected.service.CitiesConnectionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
@ComponentScan("com.citiesconnected")
public class CitiesConnectionController {

	private final Log logger = LogFactory.getLog(getClass());
	CityProcessor cityProcessor;
	CitiesConnectionService citiesConnectionService;

	CitiesConnectionController(CitiesConnectionService citiesConnectionService, CityProcessor cityProcessor) {
		this.citiesConnectionService = citiesConnectionService;
		this.cityProcessor = cityProcessor;
	}

	@GetMapping(value = "/info", produces = "text/plain")
	public String info() {
		logger.debug("in info method");
		return "Welcome to CitiesConnection App / Helps to determine easy path for your destination ";
	}

	@ApiOperation(value = "Find if a path exists between two cities", notes = "Returns yes if cites connected and no otherwise ", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 400, message = " Please check your input Origin/Destination city does not exist or invalid", response = CitiesConnectionValidationException.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Exception.class) })
	@GetMapping(value = "/connected", produces = "text/plain")
	public String isConnected(
			@ApiParam(name = "origin", value = "Origin City name", required = true) @RequestParam String origin,
			@ApiParam(name = "destination", value = "Destination City name", required = true) @RequestParam String destination) {

		City originCity = cityProcessor.getCity(origin.toUpperCase());
		City destinationCity = cityProcessor.getCity(destination.toUpperCase());

		if (originCity == null || destinationCity == null) {
			throw new CitiesConnectionValidationException(
					" Error while processing : Please cheeck your input Origin/Destination city does not exist or invalid");
		}

		return citiesConnectionService.computeRoute(originCity, destinationCity);
	}
}