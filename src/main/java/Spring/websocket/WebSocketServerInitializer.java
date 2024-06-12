//package Spring.websocket;// WebSocketServerInitializer.java
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.HttpServerCodec;
//import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
//
//public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
//
//    @Override
//    protected void initChannel(SocketChannel ch) throws Exception {
//        ChannelPipeline pipeline = ch.pipeline();
//
//        pipeline.addLast(new HttpServerCodec());
//        pipeline.addLast(new HttpServerExpectContinueHandler());
//        pipeline.addLast(new HttpObjectAggregator(65536));
//        pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
//        pipeline.addLast(new WebSocketServerHandler());
//    }
//}
