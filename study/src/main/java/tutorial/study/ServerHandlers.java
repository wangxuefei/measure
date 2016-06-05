package tutorial.study;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandlers {

	static public class HandlerOne extends ChannelHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			String data = msg.toString();
			SocketAddress address = ctx.channel().remoteAddress();
			System.out.println(address + " HandlerOne :" + data);
			

		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx) {

		}

//		@Override
//		public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//
//		}

	}

	static public class HandlerTwo extends ChannelHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			String data = msg.toString();
			SocketAddress address = ctx.channel().remoteAddress();
			System.out.println(address + " HandlerTwo :" + data);
			
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx) {

		}
//
//		@Override
//		public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//
//		}

	}

	static public class HandlerThree extends ChannelHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			String data = String.valueOf(msg);
			SocketAddress address = ctx.channel().remoteAddress();
			System.out.println(address + " HandlerThree :" + data);
			
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx) {

		}

//		@Override
//		public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//
//		}

	}

}
