package measure;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import measure.message.Message;
import measure.message.model.Function;
import measure.message.model.Meter;

public class ParseXML {

	public static void main(String[] args)
			throws DocumentException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("data/report.xml"));
		long begain = System.currentTimeMillis();

		document.selectSingleNode("/root");
		Element root = document.getRootElement();
		Message message = new Message();
		Node build = root.selectSingleNode("/root/common/building_id");
		Node sequence = root.selectSingleNode("/root/data/sequence");
		Node time = root.selectSingleNode("/root/data/time");
		List<Node> meters = root.selectNodes("/root/data/meter");
		for (Object m : meters) {
			Node meter = (Node) m;
			Meter M = new Meter();
			M.setMeter(Integer.valueOf(meter.valueOf("@id")));
			List functions = meter.selectNodes("function");
			for (Object func : functions) {
				Node function = (Node) func;
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

		System.out.println(new ObjectMapper().writeValueAsBytes(message));
		System.out.println(new ObjectMapper().readValue(new ObjectMapper().writeValueAsBytes(message), Message.class));
		System.out.println(System.currentTimeMillis() - begain);
	}

}
