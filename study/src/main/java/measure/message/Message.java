package measure.message;

import java.util.HashSet;

import measure.message.model.Meter;

public class Message {

	private long build;
	private long sequence;
	private long time;

	private HashSet<Meter> meters;

	public long getBuild() {
		return build;
	}

	public void setBuild(long build) {
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

	public HashSet<Meter> getMeters() {
		return meters;
	}

	public void setMeters(HashSet<Meter> meters) {
		this.meters = meters;
	}

	@Override
	public String toString() {
		StringBuffer helper = new StringBuffer();
		helper.append("{");
		helper.append("build").append(":").append(build).append(",");
		helper.append("sequenceNum").append(":").append(sequence).append(",");
		helper.append("meters").append(":").append("[");
		for (Meter meter : meters) {
			helper.append(meter.toString()).append(",");
		}
		helper.append("]").append("}");
		return helper.toString();
	}
}
