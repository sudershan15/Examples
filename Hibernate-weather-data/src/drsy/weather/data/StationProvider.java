package drsy.weather.data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "station_provider")
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
}
