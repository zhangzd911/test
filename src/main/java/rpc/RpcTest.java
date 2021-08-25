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
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), new MyCar());


    }

    @Test
    public void consumer() throws IOException {


    }

    @Test
    public void registr(){

    }

}
