package rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.junit.Test;
import rpc.protocol.NettyClient;
import rpc.protocol.NettyServer;
import rpc.protocol.RpcRequest;
import rpc.proxy.MyProxy;
import rpc.rpcPackage.MyContent;
import rpc.rpcPackage.MyHeader;
import rpc.service.Car;
import rpc.service.impl.MyCar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class RpcTest {




    /**
     * 服务提供方
     */
    @Test
    public void provider() throws IOException {
        //本地方法注册
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), new MyCar());


        //启动netty
        NettyServer server = new NettyServer();
        try {
            server.bind(9991);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void consumer() throws IOException {

        /*NettyClient client = new NettyClient("localhost", 9991);*/
        Car car = MyProxy.proxyGet(Car.class);
        car.ooxx("123");

        System.in.read();


    }

    public static void main(String[] args) {
        /*Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), new MyCar());*/
        Car car = MyProxy.proxyGet(Car.class);
        car.ooxx("123");
    }

}
