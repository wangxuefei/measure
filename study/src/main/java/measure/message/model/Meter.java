package measure.message.model;

import java.util.HashMap;
import java.util.HashSet;

public class Meter {

	private int meter;

	private HashSet<HashMap<String, String>> functions;

	public int getMeter() {
		return meter;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}

	public HashSet<HashMap<String, String>> getFunctions() {
		return functions;
	}

	public void setFunctions(HashSet<HashMap<String, String>> functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		StringBuffer helper = new StringBuffer();
		helper.append("{");
		helper.append("meter").append(":").append(meter).append(",");
		helper.append("functions").append(":").append("[");
		for (HashMap<String, String> map : functions) {
			helper.append("{").append("id").append(":").append(map.get("id")).append(",");
			helper.append("itemcode").append(":").append(map.get("coding")).append(",");
			helper.append("value").append(":").append(map.get("value"));
		}
		helper.append("]");
		helper.append("}");
		return helper.toString();
	}
}
