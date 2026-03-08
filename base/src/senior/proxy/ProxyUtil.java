package senior.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Performance creatProxy(Star s) {
        Performance proxy=(Performance) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                s.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //参数一：代理对象
                        //参数二：正在调用的方法
                        //参数三：调用方法时传入的参数
                        String methodName=method.getName();
                        if("sing".equals(methodName)){
                            System.out.println("准备话筒，收钱20w");
                        }else if("dance".equals(methodName)){
                            System.out.println("准备音响，收钱30w");
                        }

                        method.setAccessible(true);
                        return method.invoke(s,args);
                    }
                });
                return proxy;
    }
}
