package study.netty.c.multiclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/3
 * Desc:
 *
 * 服务器发送过去的消息也要加上\r\n
 *
 * 不然客户端接码不了
 *
 * 在这里卡了一会
 **/
public class TestChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("From Client  "+ctx.channel().remoteAddress() + " : " + msg);

        channels.forEach( c -> {
           if(c != ctx.channel())
           {
               c.writeAndFlush("From Client  " + c.remoteAddress() + " : " + msg + "\r\n");
           }
           else
           {
               c.writeAndFlush("From MySelf : " + msg+ "\r\n");
           }
        });


    }

    /**
     * 连接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        channels.writeAndFlush("[服务器] : "+channel.remoteAddress()+" Online."+ "\r\n");

        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        channels.writeAndFlush("[服务器] : "+channel.remoteAddress()+" Offline."+ "\r\n");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
