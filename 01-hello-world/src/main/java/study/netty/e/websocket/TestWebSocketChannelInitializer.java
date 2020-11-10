package study.netty.e.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Author：zhuangzhuang.hu
 * Date: 2020/11/10
 * Desc:
 **/
public class TestWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        //聚合Http消息处理器
        pipeline.addLast(new HttpObjectAggregator(8192));

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new TestWebSocketServerHandler());
    }
}
