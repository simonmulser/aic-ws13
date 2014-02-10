package com.aic.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable{

	private String username;
	
	private String password;

	public User(){}
	
	public User(String name, String pw){
		this.username = name;
		this.password = pw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	public String getSlug() {
//		return slug;
//	}
//
//	public void setSlug(String slug) {
//		this.slug = slug;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public @ByValueParameter List<SentimentResult> getSentimentResults() {
//		return sentimentResults;
//	}
//
//	public void setSentimentResults(List<SentimentResult> sentimentResults) {
//		this.sentimentResults = sentimentResults;
//	}

	@Override
	public String toString() {
//		return "Companies [slug=" + slug + ", name=" + name
//				+ ", sentimentResults=" + sentimentResults + "]";
		return getUsername();
	}
	
}
