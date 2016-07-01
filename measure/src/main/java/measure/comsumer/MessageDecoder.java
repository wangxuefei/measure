package measure.comsumer;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import kafka.serializer.Decoder;
import measure.message.Message;

public class MessageDecoder implements Decoder<Message> {

	ObjectMapper mapper;

	public MessageDecoder() {
		mapper = new ObjectMapper();
	}

	public Message fromBytes(byte[] messageBytes) {
		Message message = null;
		try {
			message = mapper.readValue(messageBytes, Message.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
