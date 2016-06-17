package measure.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class MessageCodec extends ByteToMessageCodec {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		System.out.println("Encoding ");
		ByteBuf data = (ByteBuf) msg;
		byte dst[] = new byte[data.readableBytes()];
		data.readBytes(dst);
		out.writeBytes("get data \n".getBytes());
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
		System.out.println("Decode 可读取数据大小>>>>>>" + in.readableBytes());
		if (in.readableBytes() < 3) {
			return;
		}
		byte dst[] = new byte[in.readableBytes()];
		in.readBytes(dst);
		out.add(Unpooled.wrappedBuffer(new String(dst).trim().getBytes()));
	}

}
