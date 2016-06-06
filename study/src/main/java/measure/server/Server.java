package measure.server;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	private CompositeConfiguration configuration;
	private ServerBootstrap serverBootstrap;
	private EventLoopGroup parentGroup;
	private EventLoopGroup childGroup;
	private boolean isActive = false;
	private static final Log LOG = LogFactory.getLog(Server.class);

	public Server() {
		configuration = new CompositeConfiguration();
		configuration.addConfiguration(new EnvironmentConfiguration());
		String config_path = configuration.getString("MEASURE_CONF_DIR");
		if (config_path != null) {
			String conf = config_path + "/measure.conf";
			try {
				configuration.addConfiguration(new PropertiesConfiguration(conf));
				LOG.info("Load config from. " + conf);
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				LOG.error("could not load config from " + conf, e);
				System.out.println("can`t find conf " + conf);
			}
		} else {
			LOG.warn("MEASURE_CONF_DIR  not specifies, you can Overwrite to you environment.");
		}

		init();
	}

	public Server(CompositeConfiguration configuration) {
		this.configuration = configuration;
		init();
	}

	private void init() {
		this.parentGroup = new NioEventLoopGroup(this.configuration.getInt("netty.parent.thread.size", 10));
		this.childGroup = new NioEventLoopGroup(this.configuration.getInt("netty.child.thread.size", 10));
	}

	public void run() {
		serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(this.parentGroup, this.childGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.childHandler(new ServerInitializer(configuration));
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		try {
			isActive = true;
			LOG.info("run server at :" + this.configuration.getInt("port", 10101));
			ChannelFuture future = serverBootstrap.bind(this.configuration.getInt("port", 10101)).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
			isActive = false;
			e.printStackTrace();
		}
	}

	public void stop() {
		parentGroup.shutdownGracefully();
		childGroup.shutdownGracefully();
		isActive = false;
		LOG.info("server stop");
	}

	public boolean isActive() {
		return isActive;
	}

}
