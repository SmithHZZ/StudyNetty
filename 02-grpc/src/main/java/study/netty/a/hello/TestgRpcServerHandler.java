package study.netty.a.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import study.netty.protobuf.DataInfo;

import java.time.LocalDateTime;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/12
 * Desc:
 **/
public class TestgRpcServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println("收到来自客户端"+ctx.channel().remoteAddress()+"的消息："+msg);

        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());

        DataInfo.Student student = DataInfo.Student.newBuilder().setName(LocalDateTime.now().toString()).setAge(19).setAddress("北京市东城区人民大会堂").build();

        ctx.writeAndFlush(student);
    }
}
