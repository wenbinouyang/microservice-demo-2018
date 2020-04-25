package com.oy.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping("/config")
	public String getConfig()
	{
		String str = "applicationName: " + applicationName;
		System.out.println("******str: " + str);
		return "applicationName: " + applicationName;
	}
}
