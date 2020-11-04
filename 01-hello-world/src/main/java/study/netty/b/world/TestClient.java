package study.netty.b.world;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/10/30
 * Desc:
 **/
public class TestClient {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());

            ChannelFuture future = bootstrap.connect("localhost",8899).sync();
            future.channel().closeFuture().sync();

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
