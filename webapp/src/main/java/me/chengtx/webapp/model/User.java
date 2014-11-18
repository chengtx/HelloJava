/**
 * 
 */
package me.chengtx.webapp.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author chengt4
 *
 */
@Entity
public class User {

	@Basic
	private String uid;
	@Basic
	private String name;
	@Basic
	private String email;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
