package ch.fhnw.GenZ.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Distance {

	@Id
	@GeneratedValue
	private Long id;
	private String fromCanton;
	private String toCanton;
	private Integer kilometers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCanton() {
		return fromCanton;
	}

	public void setFromCanton(String fromCity) {
		this.fromCanton = fromCity;
	}

	public String getToCanton() {
		return toCanton;
	}

	public void setToCanton(String toCity) {
		this.toCanton = toCity;
	}

	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}

}
