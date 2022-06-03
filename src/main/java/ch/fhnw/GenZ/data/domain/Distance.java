/*
 * Author: Moana Kleiner		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */
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

	public void setFromCanton(String fromCanton) {
		this.fromCanton = fromCanton;
	}

	public String getToCanton() {
		return toCanton;
	}

	public void setToCanton(String toCanton) {
		this.toCanton = toCanton;
	}

	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}

}
