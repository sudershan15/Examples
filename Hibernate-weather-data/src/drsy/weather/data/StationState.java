package drsy.weather.data;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class StationState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String state;
	
	public StationState() {
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@ForeignKey(name = "country")
	String country;
}
