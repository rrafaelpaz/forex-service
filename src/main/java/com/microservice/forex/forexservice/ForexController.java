package com.microservice.forex.forexservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
	
	//We would want to return the server port back. 
	//This will help us identify which instance service is giving the response back.
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue2(@PathVariable String from, @PathVariable String to){
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		//Get the port from environment and set it into the response bean.
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeValue;
	}
	
	
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getDogBreedById() {
		String hello = "Hello";
		return new ResponseEntity<String>(hello, HttpStatus.OK);
	}

}
