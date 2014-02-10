package com.aic.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import at.ac.tuwien.infosys.cloudscale.annotations.ByValueParameter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ByValueParameter
public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084962940154406900L;

	private String slug;
	
	private String name;
	
	private String user;

	@ByValueParameter
	private List<SentimentResult> sentimentResults;
	
	public Company(){}
	
	public Company(String slug, String name, String user, List<SentimentResult> sentimentResults){
		this.name = name;
		this.sentimentResults = sentimentResults;
		this.slug = slug;
		this.user = user;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public @ByValueParameter List<SentimentResult> getSentimentResults() {
		return sentimentResults;
	}

	public void setSentimentResults(List<SentimentResult> sentimentResults) {
		this.sentimentResults = sentimentResults;
	}

	@Override
	public String toString() {
		return "Company [slug=" + slug + ", name=" + name
				+ ", sentimentResults=" + sentimentResults + "]";
	}
	
}
