package measure.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import measure.message.Message;
import measure.message.StringXmlToMessage;

public class MessageCodec extends ByteToMessageCodec<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		System.out.println("Encoding ");
		byte[] response = StringXmlToMessage.convertToBytes(msg);
		out.writeBytes(response);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("Decode 可读取数据大小>>>>>>" + in.readableBytes());
		if (in.readableBytes() < 2500) {
			return;
		}
		byte dst[] = new byte[in.readableBytes()];
		in.readBytes(dst);
		Message message = StringXmlToMessage.convertToMessage(new String(dst));
		out.add(message);
	}

}
