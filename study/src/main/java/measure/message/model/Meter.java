package measure.message.model;

import java.util.HashSet;

public class Meter {

	private int meter;

	private HashSet<Function> functions;

	public Meter() {
		functions = new HashSet<Function>();
	}

	public int getMeter() {
		return meter;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}

	public HashSet<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(HashSet<Function> functions) {
		this.functions = functions;
	}

	public void addFunction(Function function) {
		this.functions.add(function);
	}

	@Override
	public String toString() {
		StringBuffer helper = new StringBuffer();
		helper.append("{");
		helper.append("meter").append(":").append(meter).append(",");
		helper.append("functions").append(":").append("[");
		for (Function function : functions) {
			helper.append("{").append("id").append(":").append(function.getId()).append(",");
			helper.append("itemcode").append(":").append(function.getCode()).append(",");
			helper.append("value").append(":").append(function.getValue());
			helper.append("},");
		}
		helper.append("]");
		helper.append("}");
		return helper.toString();
	}
}
