package study.netty.a.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/10/29
 * Desc:
 **/
public class TestServer {

    public static void main(String[] args) {

        //事件循环组，就是一个死循环

        //负责接收连接，将连接交给worker处理

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try
        {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture future = serverBootstrap.bind(8899).sync();

            future.channel().closeFuture().sync();
        }
        catch (Exception exc)
        {
            System.out.println(exc.getMessage());
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
