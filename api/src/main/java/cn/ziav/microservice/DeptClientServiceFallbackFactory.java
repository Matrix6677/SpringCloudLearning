package cn.ziav.microservice;

import cn.ziav.microservice.entity.Dept;
import cn.ziav.microservice.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
  @Override
  public Object create(Throwable throwable) {
    return new DeptClientService() {
      @Override
      public Dept get(long id) {
        return new Dept()
            .setDeptno(id)
            .setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
            .setDb_source("no this database in MySQL");
      }

      @Override
      public List<Dept> list() {
        return null;
      }

      @Override
      public boolean add(Dept dept) {
        return false;
      }
    };
  }
}
