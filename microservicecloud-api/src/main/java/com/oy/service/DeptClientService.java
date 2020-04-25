package com.oy.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oy.entities.Dept;

//@FeignClient("MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	@PostMapping("/dept")
	public boolean add(@RequestBody Dept dept);

	@GetMapping("/dept/{id}")
	public Dept get(@PathVariable("id") Long id);

	@GetMapping("/dept")
	public List<Dept> list();
}
