package com.test.one.ConnectingCities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.one.ConnectingCities.model.RoadMap;

@RestController
public class DestinationController {
	@Autowired
	RoadMap roadMap;
	@GetMapping("/connected")
	public boolean hasPath(@RequestParam("origin") String origin,@RequestParam("destination")String destination) {
		origin =  origin.replaceAll("\\s+", "");
		destination =  destination.replaceAll("\\s+", "");
		return  roadMap.hasRoad(origin, destination);
	}
}
