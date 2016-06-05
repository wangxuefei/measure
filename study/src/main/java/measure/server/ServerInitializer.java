package measure.server;




import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.EnvironmentConfiguration;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import measure.handler.Dealer;
import measure.handler.Decode;
import measure.handler.Encode;


public class ServerInitializer extends ChannelInitializer<SocketChannel> {
	
	private  CompositeConfiguration configuration = new CompositeConfiguration();
	
	public ServerInitializer() {
		configuration.addConfiguration( new EnvironmentConfiguration());
	}
	
	public ServerInitializer(Configuration configuration){
		this.configuration.addConfiguration(configuration);
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		 ChannelPipeline pipeline = ch.pipeline();
		 pipeline.addFirst(new Decode());
		 pipeline.addLast(new Dealer(configuration));
		 pipeline.addLast(new Encode());

	}

}
