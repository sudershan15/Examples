package drsy.weather.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "station")
@Inheritance(strategy=InheritanceType.JOINED)
public class Station {
	
	@Id
	@Column(name="id", nullable = false)
	String id;
	String sec_id;
	String name;
	@Embedded
	@ForeignKey(name = "state")
	StationState sskey;
	
	float latitude;
	float longitude;
	float elevation;
	@ForeignKey(name = "network")
	int network;
	String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "station")
	private Collection<Wdata> wdatas;
	
	public void removeWdata(Wdata c) {
		if (wdatas == null)
			return;

		wdatas.remove(c);
	}

	public void addWdata(Wdata c) {
		if (wdatas == null)
			wdatas = new ArrayList<Wdata>();

		wdatas.add(c);
	}

	public Collection<Wdata> getWdatas() {
		return wdatas;
	}
	public void setWdatas(Collection<Wdata> wdatas) {
		this.wdatas = wdatas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSec_id() {
		return sec_id;
	}
	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StationState getSskey() {
		return sskey;
	}
	public void setSskey(StationState sskey) {
		this.sskey = sskey;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getElevation() {
		return elevation;
	}
	public void setElevation(float elevation) {
		this.elevation = elevation;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Station() {
		sskey = new StationState();
	}
	public String getState() {
		return sskey.getState();
	}

	public void setState(String state) {
		sskey.state = state;
	}

	public String getCountry() {
		return sskey.getCountry();
	}

	public void setCountry(String country) {
		sskey.country = country;
	}
}
