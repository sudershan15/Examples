package drsy.weather.data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "wdata")
public class Wdata {

	@Id
	@Embedded
	WdataKey key;
	float tmpf;
	float sknt;
	float drct;
	float gust;
	float pmsl;
	float alti;
	float dwpf;
	float relh;
	float wthr;
	float p24i;
	
	@ForeignKey(name = "network")
	int network;
	
	public Wdata() { key = new WdataKey(); }
	
	public WdataKey getKey() {
		return key;
	}
	public void setKey(WdataKey key) {
		this.key = key;
	}
	public float getTmpf() {
		return tmpf;
	}
	public void setTmpf(float tmpf) {
		this.tmpf = tmpf;
	}
	public float getSknt() {
		return sknt;
	}
	public void setSknt(float sknt) {
		this.sknt = sknt;
	}
	public float getDrct() {
		return drct;
	}
	public void setDrct(float drct) {
		this.drct = drct;
	}
	public float getGust() {
		return gust;
	}
	public void setGust(float gust) {
		this.gust = gust;
	}
	public float getPmsl() {
		return pmsl;
	}
	public void setPmsl(float pmsl) {
		this.pmsl = pmsl;
	}
	public float getAlti() {
		return alti;
	}
	public void setAlti(float alti) {
		this.alti = alti;
	}
	public float getDwpf() {
		return dwpf;
	}
	public void setDwpf(float dwpf) {
		this.dwpf = dwpf;
	}
	public float getRelh() {
		return relh;
	}
	public void setRelh(float relh) {
		this.relh = relh;
	}
	public float getWthr() {
		return wthr;
	}
	public void setWthr(float wthr) {
		this.wthr = wthr;
	}
	public float getP24i() {
		return p24i;
	}
	public void setP24i(float p24i) {
		this.p24i = p24i;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getTime() {
		return key.getTime();
	}
	public void setTime(int time) {
		key.time = time;
	}
	public int getDate() {
		return key.getDate();
	}
	public void setDate(int date) {
		key.date = date;
	}
	public String getStation() {
		return key.getStation();
	}
	public void setStation(String station) {
		key.station = station;
	}
}
