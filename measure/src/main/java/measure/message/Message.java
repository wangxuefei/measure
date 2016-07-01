package measure.message;

import java.util.ArrayList;

import measure.message.model.Meter;

public class Message {

	private String build;
	private long sequence;
	private long time;

	// private HashSet<Meter> meters;
	private ArrayList<Meter> meters;

	public Message() {
		// meters = new HashSet<Meter>();
		meters = new ArrayList<Meter>();
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public ArrayList<Meter> getMeters() {
		return meters;
	}

	public void setMeters(ArrayList<Meter> meters) {
		this.meters = meters;
	}

	public void addMeter(Meter meter) {
		this.meters.add(meter);
	}

	@Override
	public String toString() {
		StringBuffer helper = new StringBuffer();
		helper.append("{");
		helper.append("build").append(":").append(build).append(",");
		helper.append("time").append(":").append(time).append(",");
		helper.append("sequenceNum").append(":").append(sequence).append(",");
		helper.append("meters").append(":").append("[");
		for (Meter meter : meters) {
			helper.append(meter.toString()).append(",");
		}
		helper.deleteCharAt(helper.lastIndexOf(","));
		helper.append("]").append("}");
		return helper.toString();
	}
}
