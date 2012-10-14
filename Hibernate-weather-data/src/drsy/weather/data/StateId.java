package drsy.weather.data;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.annotations.ForeignKey;


@Embeddable
public class StateId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ForeignKey(name = "country")
	String country_id;
	
	String id;
	
	public StateId() { }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get_countryid() {
		return country_id;
	}

	public void set_countryid(String _countryid) {
		this.country_id = _countryid;
	}
}
