package measure.server;





import org.apache.commons.configuration.Configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	public void run(Configuration configuration) throws InterruptedException {
		// TODO Auto-generated method stub
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		serverBootstrap.group(parentGroup, childGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.childHandler(new ServerInitializer(configuration));
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		
		ChannelFuture f = serverBootstrap.bind(8090).sync();
		f.channel().closeFuture().sync();
	}

}
