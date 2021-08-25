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
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = boss;
        ServerBootstrap sbs = new ServerBootstrap();

        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("server accept cliet port: " + ch.remoteAddress().getPort());

                        ChannelPipeline p  = ch.pipeline();

                        p.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(1024*512))
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        //http 协议 ,  这个msg是一个啥：完整的http-request
                                        FullHttpRequest request = (FullHttpRequest) msg;
                                        System.out.println(request.toString());  //因为现在sonsumer使用的是一个现成的URL

                                    }
                                });
                    }
                }).bind(new InetSocketAddress("localhost", 9090));
        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
