package ch.fhnw.GenZ.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransportCost {

	@Id
	@GeneratedValue
	private Long id;
	private Integer km;
	private Double pal;
	private Double cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Double getPal() {
		return pal;
	}

	public void setPal(Double pal) {
		this.pal = pal;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
