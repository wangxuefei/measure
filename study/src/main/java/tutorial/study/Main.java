package tutorial.study;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		serverBootstrap.group(parentGroup, childGroup)
						.channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								 ch.pipeline().addLast( new ServerHandlers.HandlerOne());
								 ch.pipeline().addLast(new ServerHandlers.HandlerTwo());
								 ch.pipeline().addLast(new ServerHandlers.HandlerThree());
								
							}
						})
						.option(ChannelOption.SO_BACKLOG, 128)
						.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture f = serverBootstrap.bind(8090).sync();
		f.channel().closeFuture().sync();
	}

}
