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
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), new MyCar());


    }

    @Test
    public void consumer() throws IOException {

        NettyClient client = new NettyClient("localhost", 9991);
        try {
            client.start();
            Channel channel = client.getChannel();


            RpcRequest request = new RpcRequest();
            MyHeader myHeader = new MyHeader();
            myHeader.setFlag(1);
            myHeader.setDataLen(1);
            myHeader.setRequestID(20210825L);
            MyContent myContent = new MyContent();
            myContent.setArgs(new Object[1]);
            myContent.setName("123");
            myContent.setMethodName("456");
            myContent.setParameterTypes(new Class[3]);
            myContent.setArgs(new Class[3]);
            request.setId("123456");
            request.setContent(myContent);
            request.setHeader(myHeader);

            channel.writeAndFlush(request);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void registr(){

        NettyServer server = new NettyServer();
        try {
            server.bind(9991);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
