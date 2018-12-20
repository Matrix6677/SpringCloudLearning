package cn.ziav.microservice.controller;

import cn.ziav.microservice.entity.Dept;
import cn.ziav.microservice.service.DeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
  @Autowired private DeptService service;

  @Autowired private DiscoveryClient discoveryClient;

  @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
  public boolean add(@RequestBody Dept dept) {
    return service.add(dept);
  }

  @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
  public Dept get(@PathVariable("id") Long id) {
    return service.get(id);
  }

  @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
  public List<Dept> list() {
    return service.list();
  }

  @GetMapping("/dept/discovery")
  public Object discovery() {
    List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICE-DEPT");
    System.err.println(srvList);
    for (ServiceInstance instance : srvList) {
      System.err.println(instance);
    }

    return discoveryClient;
  }
}
