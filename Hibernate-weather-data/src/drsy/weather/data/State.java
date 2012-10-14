package drsy.weather.data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State {
	
	@Id
	@Embedded
/*	@Column(insertable = false, updatable = false, nullable = false)*/
	private StateId primaryKey;
	@Column
	String name;
	public State() {
		primaryKey = new StateId();
	}
	public StateId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(StateId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return primaryKey.getId();
	}

	public void setId(String id) {
		primaryKey.id = id;
	}

	public String get_countryid() {
		return primaryKey.get_countryid();
	}

	public void set_countryid(String _countryid) {
		primaryKey.country_id = _countryid;
	}
}
