package measure.message;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import measure.message.model.Function;
import measure.message.model.Meter;

public class StringXmlToMessage {

	@SuppressWarnings("unchecked")
	public static Message convertToMessage(String xmlString) throws DocumentException {
		Message message = new Message();
		Document document = DocumentHelper.parseText(xmlString);
		Element root = document.getRootElement();
		Node build = root.selectSingleNode("/root/common/building_id");
		Node sequence = root.selectSingleNode("/root/data/sequence");
		Node time = root.selectSingleNode("/root/data/time");
		List<Node> meters = root.selectNodes("/root/data/meter");
		for (Node meter : meters) {
			Meter M = new Meter();
			M.setMeter(Integer.valueOf(meter.valueOf("@id")));
			List<Node> functions = meter.selectNodes("function");
			for (Node function : functions) {
				Function F = new Function();
				F.setId(Integer.valueOf(function.valueOf("@id")));
				F.setCode(function.valueOf("@coding"));
				F.setErrorCode(function.valueOf("@error"));
				F.setValue(Float.valueOf(function.getText()));
				M.addFunction(F);
			}
			message.addMeter(M);
		}
		message.setBuild(build.getText());
		message.setSequence(Long.valueOf(sequence.getText()));
		message.setTime(Long.valueOf(time.getText()));
		return message;
	}

	public static byte[] convertToBytes(Message message)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(message);
	}
}
