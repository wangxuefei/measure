package measure.message.model;

public class Function {

	private int id;
	private String code;
	private String errorCode;
	private float value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getValue() {
		return this.value;
	}

}
