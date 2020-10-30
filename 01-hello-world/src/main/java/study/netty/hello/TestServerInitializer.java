package study.netty.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/10/29
 * Desc: TestServerInitializer
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        pipeline.addLast("TestHttpServerHandler",new TestHttpServerHandler());


    }
}
