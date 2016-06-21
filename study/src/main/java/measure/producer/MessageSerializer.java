package measure.producer;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import measure.message.Message;

public class MessageSerializer implements Serializer<Message> {

	public void configure(Map<String, ?> configs, boolean isKey) {
		// donothing
	}

	public byte[] serialize(String topic, Message data) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] D = null;
		try {
			D = mapper.writeValueAsBytes(data);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return D;
	}

	public void close() {

	}

}
