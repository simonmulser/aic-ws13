package com.aic.entities;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Companies implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084962940154406900L;

	private String slug;
	
	private String name;
	
	private String user;


	private List<SentimentResult> sentimentResults;
	
	public Companies(){}
	
	public Companies(String slug, String name, String user, List<SentimentResult> sentimentResults){
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

	public  List<SentimentResult> getSentimentResults() {
		return sentimentResults;
	}

	public void setSentimentResults(List<SentimentResult> sentimentResults) {
		this.sentimentResults = sentimentResults;
	}

	@Override
	public String toString() {
		return "Companies [slug=" + slug + ", name=" + name
				+ ", sentimentResults=" + sentimentResults + "]";
	}
	
}
