package measure.server;

import org.apache.commons.configuration.Configuration;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import measure.handler.Dealer;
import measure.handler.MessageCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	private Configuration configuration;

	public ServerInitializer(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addFirst(new MessageCodec());
		pipeline.addLast(new Dealer(this.configuration));

	}

}
