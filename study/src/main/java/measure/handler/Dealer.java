package measure.handler;



import org.apache.commons.configuration.Configuration;

import io.netty.channel.ChannelHandlerAdapter;
import measure.producer.Postman;

public class Dealer extends ChannelHandlerAdapter {
	
	private Postman postman ;
	
	
	public Dealer(Configuration configuration){
		postman = new Postman(configuration);
	}
	
	
	
}
