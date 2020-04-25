package com.oy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oy.entities.Dept;
import com.oy.service.DeptService;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService service;

	@PostMapping("/dept")
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@GetMapping("/dept/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@GetMapping("/dept")
	public List<Dept> list() {
		return service.list();
	}

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("client.getServices: " + list);

		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}

}
