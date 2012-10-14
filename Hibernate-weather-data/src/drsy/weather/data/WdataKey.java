package drsy.weather.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class WdataKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WdataKey() { }
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	int time;
	@Column(nullable = false)
	int date;
	@ForeignKey(name = "station")
	String station;
}
