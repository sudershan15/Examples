package drsy.weather.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.mapping.Collection;

@Embeddable
public class StationProviderKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public int getProvider() {
		return provider;
	}
	public void setProvider(int provider) {
		this.provider = provider;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public StationProviderKey() {
		// TODO Auto-generated constructor stub
	}

	@ForeignKey(name="station")
	String station;
	@ForeignKey(name="provider")
	int provider;
	

	int type;
}
