package drsy.weather.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Provider {
	
	@Id
	@Column(name = "id", updatable = false, insertable = false, nullable = false)
	int id;
	
	@Column(name = "name", nullable = false)
	String name;	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
