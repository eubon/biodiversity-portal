package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USER_REQUESTS database table.
 * 
 */
@Entity
@Table(name="USER_REQUESTS")
@NamedQuery(name="UserRequest.findAll", query="SELECT u FROM UserRequest u")
public class UserRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_REQUESTS_ID_GENERATOR", sequenceName="USER_REQUESTS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_REQUESTS_ID_GENERATOR")
	private long id;

	@Column(name="INTERFACE_TYPE")
	private String interfaceType;

	@Column(name="REQ_DATE")
	private Date reqDate;

	@Column(name="REQ_TEXT")
	private String reqText;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="userRequest")
	private List<Request> requests;

	public UserRequest() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInterfaceType() {
		return this.interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
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

	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setUserRequest(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setUserRequest(null);

		return request;
	}

}