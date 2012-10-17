package drsy.weather.data;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.mapping.Collection;

@Entity
@Table(name = "station_provider")
@Inheritance(strategy=InheritanceType.JOINED)
public class StationProvider {
	
	@Embedded
	StationProviderKey key;

	public StationProvider() {
		key = new StationProviderKey();
	}
	public StationProviderKey getKey() {
		return key;
	}

	public void setKey(StationProviderKey key) {
		this.key = key;
	}
	
	public String getStation() {
		return key.getStation();
	}
	public void setStation(String station) {
		key.station = station;
	}
	public int getProvider() {
		return key.getProvider();
	}
	public void setProvider(int provider) {
		key.provider = provider;
	}
	public int getType() {
		return key.getType();
	}
	public void setType(int type) {
		key.type = type;
	}
	
	public Station getStations() {
		return stations;
	}
	public void setStations(Station stations) {
		this.stations = stations;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "station")
	private Station stations;
}
