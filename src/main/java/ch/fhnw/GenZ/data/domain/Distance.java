package ch.fhnw.GenZ.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Distance {

	@Id
	@GeneratedValue
	private Long id;
	private String fromCity;
	private String toCity;
	private Integer kilometers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}

}
