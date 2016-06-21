package measure.handler;

import org.apache.commons.configuration.Configuration;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import measure.message.Message;
import measure.producer.Postman;

public class Dealer extends ChannelHandlerAdapter {

	private static Postman postman;

	public Dealer(Configuration configuration) {
		postman = new Postman(configuration);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Message message = (Message) msg;
		postman.send(message);
		System.out.println(message);
		ctx.writeAndFlush(message);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
