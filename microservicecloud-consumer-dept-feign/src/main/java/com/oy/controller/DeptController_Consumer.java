package com.oy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oy.entities.Dept;
import com.oy.service.DeptClientService;

@RestController
public class DeptController_Consumer {

	@Autowired
	private DeptClientService deptClientService;

//	@Autowired
//	private RestTemplate restTemplate;

	@PostMapping("/consumer/dept")
	public boolean add(Dept dept) {
		return deptClientService.add(dept);
		//return restTemplate.postForObject(REST_URL_PREFIX + "/dept", dept, Boolean.class);
	}

	@GetMapping("/consumer/dept/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return deptClientService.get(id);
		//return restTemplate.getForObject(REST_URL_PREFIX + "/dept/" + id, Dept.class);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/consumer/dept")
	public List<Dept> list() {
		return deptClientService.list();
		//return restTemplate.getForObject(REST_URL_PREFIX + "/dept", List.class);
	}

	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
//	@RequestMapping(value = "/consumer/dept/discovery")
//	public Object discovery() {
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
//	}

}
