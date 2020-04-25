package com.oy.service;

import java.util.List;

import com.oy.entities.Dept;

public interface DeptService {
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
}
