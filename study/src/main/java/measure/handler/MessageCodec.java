package measure.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class MessageCodec extends ByteToMessageCodec {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

		ByteBuf data = (ByteBuf) msg;
		byte dst[] = new byte[data.readableBytes()];
		data.readBytes(dst);
		System.out.println("Encoding " + new String(dst));
		out.writeBytes((ByteBuf) msg);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
		System.out.println("Decoding");
		out.add(in);
	}

}
