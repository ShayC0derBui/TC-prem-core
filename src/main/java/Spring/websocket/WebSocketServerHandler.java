//package Spring.websocket;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import  Spring.callRest.*;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.websocketx.*;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
//    BinanceMapHandler binanceMapHandler=new BinanceMapHandler();
//    ByBitMapHandler byBitMapHandler=new ByBitMapHandler();
//    KucoinMapHandler kucoinMapHandler=new KucoinMapHandler();
//    GateioMapHandler gateioMapHandler=new GateioMapHandler();
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof WebSocketFrame) {
//            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
//        }
//    }
//    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws IOException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException {
//        if (frame instanceof TextWebSocketFrame) {
//            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
//            String receivedText = textFrame.text();
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(receivedText, JsonObject.class);
//            JsonElement exch = jsonObject.get("exchange");
//            String exchangeName = exch.getAsString();
//            switch (exchangeName) {
//                case "Binance":
//                    binanceMapHandler.invokeBinance(textFrame);
//                    break;
//                case "Bybit":
//                    byBitMapHandler.invokeBybit(textFrame);
//                    break;
//                case "Gateio":
//                    gateioMapHandler.invokeGateio(textFrame);
//                    break;
//                case "Kucoin":
//                    kucoinMapHandler.invokeKucoin(textFrame);
//                    break;
//                default:
//                    System.out.println("Invalid Exchange");
//                    break;
//            }
//            String echoedMessage="Server Echo";
//            ctx.writeAndFlush(new TextWebSocketFrame(echoedMessage));
//        }
//    }
//
//}
//
//
//
//
//
//
