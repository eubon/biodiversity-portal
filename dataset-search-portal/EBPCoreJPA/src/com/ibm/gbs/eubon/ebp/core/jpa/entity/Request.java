package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the REQUESTS database table.
 * 
 */
@Entity
@Table(name="REQUESTS")
@NamedQuery(name="Request.findAll", query="SELECT r FROM Request r")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REQUESTS_ID_GENERATOR", sequenceName="REQUESTS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUESTS_ID_GENERATOR")
	private long id;

	@Column(name="REQ_DATE")
	private Date reqDate;

	@Column(name="REQ_TEXT")
	private String reqText;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	private Provider provider;

	//bi-directional many-to-one association to UserRequest
	@ManyToOne
	@JoinColumn(name="USER_REQ_ID")
	private UserRequest userRequest;

	public Request() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getReqDate() {
		return this.reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqText() {
		return this.reqText;
	}

	public void setReqText(String reqText) {
		this.reqText = reqText;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public UserRequest getUserRequest() {
		return this.userRequest;
	}

	public void setUserRequest(UserRequest userRequest) {
		this.userRequest = userRequest;
	}

}