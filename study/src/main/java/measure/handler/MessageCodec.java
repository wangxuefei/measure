package measure.handler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import measure.message.Message;
import measure.message.StringXmlToMessage;

public class MessageCodec extends ByteToMessageCodec<Message> {
	private static final Log LOG = LogFactory.getLog(MessageCodec.class);
	
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
		Message message = null;
		try {
			 message = StringXmlToMessage.convertToMessage(new String(dst));
		}catch(DocumentException e){
			ctx.writeAndFlush(e);
		}
		LOG.info("get data from "+ctx.channel().remoteAddress());
		out.add(message);
	}

}
