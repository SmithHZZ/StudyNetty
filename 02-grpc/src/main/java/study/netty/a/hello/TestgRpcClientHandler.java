package study.netty.a.hello;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import study.netty.protobuf.DataInfo;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/12
 * Desc:
 **/
public class TestgRpcClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println("收到来自服务器"+ctx.channel().remoteAddress()+"的消息："+msg);

        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("Client").setAge(18).setAddress("北京市东城区中南海").build();
        ctx.writeAndFlush(student);
    }
}
