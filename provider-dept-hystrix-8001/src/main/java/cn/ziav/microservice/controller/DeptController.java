package cn.ziav.microservice.controller;

import cn.ziav.microservice.entity.Dept;
import cn.ziav.microservice.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
  @Autowired private DeptService service;

  @Autowired private DiscoveryClient discoveryClient;

  @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
  public boolean add(@RequestBody Dept dept) {
    return service.add(dept);
  }

  @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
  @HystrixCommand(fallbackMethod = "processHystrix_Get")
  public Dept get(@PathVariable("id") Long id) {
    Dept dept = this.service.get(id);
    if (null == dept) {
      throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
    }
    return dept;
  }

  public Dept processHystrix_Get(@PathVariable("id") Long id) {
    return new Dept()
        .setDeptno(id)
        .setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
        .setDb_source("no this database in MySQL");
  }

  @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
  public List<Dept> list() {
    return service.list();
  }

  @GetMapping("/dept/discovery")
  public Object discovery() {
    List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
    System.err.println(srvList);
    for (ServiceInstance instance : srvList) {
      System.err.println(instance);
    }

    return discoveryClient;
  }
}
