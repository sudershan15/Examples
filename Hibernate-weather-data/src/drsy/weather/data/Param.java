package drsy.weather.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "param")
public class Param {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	String id;
	
	@Column(name = "descrip", nullable = false)
	String descrip;
	String eunits;
	String munits;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getEunits() {
		return eunits;
	}

	public void setEunits(String eunits) {
		this.eunits = eunits;
	}

	public String getMunits() {
		return munits;
	}

	public void setMunits(String munits) {
		this.munits = munits;
	}
}
