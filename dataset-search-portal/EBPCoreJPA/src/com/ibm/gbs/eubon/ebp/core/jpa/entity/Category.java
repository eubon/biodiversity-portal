package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CATEGORY database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORY_ID_GENERATOR", sequenceName="CATEGORY_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="category")
	private List<Content> contents;

	public Category() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Content> getContents() {
		return this.contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public Content addContent(Content content) {
		getContents().add(content);
		content.setCategory(this);

		return content;
	}

	public Content removeContent(Content content) {
		getContents().remove(content);
		content.setCategory(null);

		return content;
	}

}