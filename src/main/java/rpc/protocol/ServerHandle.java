package rpc.protocol;

import rpc.protocol.RpcRequest;
import rpc.protocol.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import rpc.proxy.MyProxy;
import rpc.rpcPackage.MyContent;
import rpc.rpcPackage.MyHeader;
import rpc.service.Car;

import java.util.UUID;

public class ServerHandle extends ChannelInboundHandlerAdapter{

    //接受client发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        System.out.println("接收到客户端信息:" + request.toString());


        //对请求头校验等
        MyContent myContent = request.getContent();

        Object o = MyProxy.proxyGet(Class.forName(myContent.getName()));

        long requestID = request.getHeader().getRequestID();


        //返回的数据结构
        RpcResponse response = new RpcResponse();
        MyHeader resHeader = new MyHeader();
        resHeader.setFlag(2);
        resHeader.setRequestID(requestID);
        resHeader.setDataLen(1234);
        myContent.setRes(o);
        response.setHeader(resHeader);
        response.setContent(myContent);

        ctx.writeAndFlush(response);
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush("hello client");
    }
}