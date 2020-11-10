package study.netty.e.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/10
 * Desc:
 **/
public class TestWebSocketServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TestWebSocketChannelInitializer());

            ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            future.channel().closeFuture().sync();

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }

}
