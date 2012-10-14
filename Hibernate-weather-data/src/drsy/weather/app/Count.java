package drsy.weather.app;

public class Count {

	float tmpf;
	
	public Count(float tmpf) {
		// TODO Auto-generated constructor stub
		this.tmpf = tmpf;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Value of tmpf");
		sb.append(": ");
		sb.append(tmpf);

		return sb.toString();
	}
	
	public float getTmpf() {
		return tmpf;
	}

	public void setTmpf(float tmpf) {
		this.tmpf = tmpf;
	}

}
