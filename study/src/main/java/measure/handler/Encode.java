package measure.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Encode extends MessageToByteEncoder<Byte[]>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Byte[] msg, ByteBuf out) throws Exception {
		
		
	}

}
