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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

	// 服务熔断 start
	@GetMapping("/dept/{id}")
	// 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = this.service.get(id);
		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDname("该id："+ id + " 没有对应的信息。");
		dept.setDb_source("no this database in MySQL");
		return dept;
	}
	// 服务熔断 end

	@GetMapping("/dept")
	public List<Dept> list() {
		return service.list();
	}

	// 服务发现 start
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
	// 服务发现 end
}
