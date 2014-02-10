package com.aic.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;

import at.ac.tuwien.infosys.cloudscale.annotations.ByValueParameter;

import com.aic.rest.DateTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ByValueParameter
public class Analysis implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3411543777447835509L;

	private int id;

	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime started;
	
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime finished;

	@ByValueParameter
	private List<Company> companies;

	public Analysis(){}

	public Analysis(DateTime started, List<Company> companies) {
		this.started = started;
		this.companies = companies;
	}

	public DateTime getStarted() {
		return started;
	}

	public void setStarted(DateTime started) {
		this.started = started;
	}

	public DateTime getFinished() {
		return finished;
	}

	public void setFinished(DateTime finished) {
		this.finished = finished;
	}

	public @ByValueParameter List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Analysis [id=" + id + ", started=" + started + ", finished="
				+ finished + ", companies=" + companies + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Analysis other = (Analysis) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
