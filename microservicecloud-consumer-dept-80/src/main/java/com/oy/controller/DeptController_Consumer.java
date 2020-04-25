package com.oy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oy.entities.Dept;

@RestController
public class DeptController_Consumer {

	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

	/**
	 * 使用 使用restTemplate访问restful接口。
	 *  (url, requestMap, ResponseBean.class)
	 *       这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
	 */
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/consumer/dept")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept", dept, Boolean.class);
	}

	@GetMapping("/consumer/dept/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/" + id, Dept.class);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/consumer/dept")
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept", List.class);
	}

	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}

}
