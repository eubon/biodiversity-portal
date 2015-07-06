package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the CONTENT database table.
 * 
 */
@Entity
@NamedQuery(name="Content.findAll", query="SELECT c FROM Content c")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTENT_ID_GENERATOR", sequenceName="CONTENT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTENT_ID_GENERATOR")
	private long id;

	private String description;

	@Column(name="FLG_PUBLISH")
	private boolean flgPublish;

	@Column(name="LINK")
	private String link;

	@Column(name="PUBLISH_DATE")
	private Date publishDate;

	private String title;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="ID_CATEGORY")
	private Category category;

	public Content() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getFlgPublish() {
		return this.flgPublish;
	}

	public void setFlgPublish(boolean flgPublish) {
		this.flgPublish = flgPublish;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}