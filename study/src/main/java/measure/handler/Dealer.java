package measure.handler;

import org.apache.commons.configuration.Configuration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import measure.producer.Postman;

public class Dealer extends ChannelHandlerAdapter {

	private final Postman postman;

	public Dealer(Configuration configuration) {
		postman = new Postman(configuration);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf m = (ByteBuf) msg;
		ByteBuf m_cp = m.copy();
		byte dst[] = new byte[m.readableBytes()];
		m.readBytes(dst);
		postman.send(new String(dst));
		ctx.writeAndFlush(m_cp);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
