package rpc.proxy;

import io.netty.channel.Channel;
import rpc.Dispatcher;
import rpc.protocol.NettyClient;
import rpc.protocol.RpcRequest;
import rpc.rpcPackage.MyContent;
import rpc.rpcPackage.MyHeader;
import rpc.service.Car;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MyProxy {

    //动态代理方法
    public static <T>T proxyGet(Class<T>  interfaceInfo){
        //实现各个版本的动态代理。。。。

        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};

        //TODO  LOCAL REMOTE  实现：  用到dispatcher  直接给你返回，还是本地调用的时候也代理一下

        Dispatcher dis =Dispatcher.getDispatcher();
        return (T) Proxy.newProxyInstance(loader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object res=null;
                Object o = dis.get(interfaceInfo.getName());
                if(o== null){

                    doRpcRequest(interfaceInfo, method, args);

                } else {
                    //就是local
                    //插入一些插件的机会，做一些扩展
                    System.out.println("lcoal FC....");
                    Class<?> clazz = o.getClass();
                    try {
                        Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                        res = m.invoke(o, args);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
                return  res;

                //TODO 应该在service的方法执行的时候确定是本地的还是远程的，用到dispatcher来区分下

            }

            private void doRpcRequest(Class<T>  interfaceInfo, Method method, Object[] args) {
                NettyClient client = new NettyClient("localhost", 9991);
                try {
                    client.start();
                    Channel channel = client.getChannel();



                    RpcRequest request = new RpcRequest();
                    MyHeader myHeader = new MyHeader();
                    myHeader.setFlag(1);
                    //myHeader.setDataLen(1);
                    myHeader.setRequestID(20210825L);
                    MyContent myContent = new MyContent();
                    myContent.setName(interfaceInfo.getName());
                    myContent.setMethodName(method.getName());
                    myContent.setParameterTypes(method.getParameterTypes());
                    myContent.setArgs(args);

                    request.setId(UUID.randomUUID().toString());
                    request.setContent(myContent);
                    request.setHeader(myHeader);

                    channel.writeAndFlush(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
