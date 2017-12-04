package com.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author huanpeng.ling (421620330@qq.com) on 2017/5/23.
 */

//使用动态代理
public class ProxyTest {
  public static void main(String[] args) {
    Service service = new UserServiceImpl();
    MyInvocatioHandler handler = new MyInvocatioHandler(service);
    Service serviceProxy = (Service) handler.getProxy();
    serviceProxy.add();
  }

  //定义业务逻辑
  public interface Service {
    //目标方法
    public abstract void add();
  }

  public static class UserServiceImpl implements Service {
    public void add() {
      System.out.println("This is add service");
    }
  }

  //利用java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口定义代理类的实现。
  static class MyInvocatioHandler implements InvocationHandler {
    private Object target;

    public MyInvocatioHandler(Object target) {
      this.target = target;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("-----before-----");
      Object result = method.invoke(target, args);
      System.out.println("-----end-----");
      return result;
    }

    // 生成代理对象
    public Object getProxy() {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      Class[] interfaces = target.getClass().getInterfaces();
      return Proxy.newProxyInstance(loader, interfaces, this);
    }
  }
}
