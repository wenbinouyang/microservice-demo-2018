package com.oy.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oy.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
	@Override
	public DeptClientService create(Throwable throwable) {
		return new DeptClientService() {
			@Override
			public Dept get(Long id) {
				Dept dept = new Dept();
				dept.setDeptno(id);
				dept.setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
				dept.setDb_source("no this database in MySQL");
				return dept;
			}

			// 其他方法省略
			@Override
			public boolean add(Dept dept) {
				return false;
			}

			@Override
			public List<Dept> list() {
				return null;
			}
		};
	}
}
