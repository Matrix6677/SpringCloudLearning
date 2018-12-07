package cn.ziav.microservice.service;

import cn.ziav.microservice.entity.Dept;

import java.util.List;

public interface DeptService {
  boolean add(Dept dept);

  Dept get(Long id);

  List<Dept> list();
}
