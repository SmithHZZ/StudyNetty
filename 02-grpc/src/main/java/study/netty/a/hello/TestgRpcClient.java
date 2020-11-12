package study.netty.a.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/12
 * Desc:
 **/
public class TestgRpcClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new TestgRpcClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            channel.closeFuture().sync();
        } catch (Exception exc) {

            System.out.println(exc.getMessage());

        } finally {
            group.shutdownGracefully();
        }

    }
}
