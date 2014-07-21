/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import java.util.Date;

import com.prodyna.pac.ressys.basis.model.BasisRessysEntity;

/**
 * Entity for user representation.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
public class User extends BasisRessysEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264167079588857563L;

	private String userName;
	private String loginName;
	private String password;
	private String eMail;
	private String licenceId;
	private Date licenceValidUntilDate;
	
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	/**
	 * @param userName
	 * @param loginName
	 * @param password
	 * @param eMail
	 * @param licenceId
	 * @param licenceValidUntilDate
	 */
	public User(String userName, String loginName, String password,
			String eMail, String licenceId, Date licenceValidUntilDate) {
		super();
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.eMail = eMail;
		this.licenceId = licenceId;
		this.licenceValidUntilDate = licenceValidUntilDate;
	}
	
	/**
	 * @param id
	 * @param userName
	 * @param loginName
	 * @param password
	 * @param eMail
	 * @param licenceId
	 * @param licenceValidUntilDate
	 */
	public User(long id, String userName, String loginName, String password,
			String eMail, String licenceId, Date licenceValidUntilDate) {
		super(id);
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.eMail = eMail;
		this.licenceId = licenceId;
		this.licenceValidUntilDate = licenceValidUntilDate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the licenceId
	 */
	public String getLicenceId() {
		return licenceId;
	}

	/**
	 * @param licenceId the licenceId to set
	 */
	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}

	/**
	 * @return the licenceValidUntilDate
	 */
	public Date getLicenceValidUntilDate() {
		return licenceValidUntilDate;
	}

	/**
	 * @param licenceValidUntilDate the licenceValidUntilDate to set
	 */
	public void setLicenceValidUntilDate(Date licenceValidUntilDate) {
		this.licenceValidUntilDate = licenceValidUntilDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + getId() + ", userName=" + userName + ", loginName="
				+ loginName + ", password=" + password + ", eMail=" + eMail
				+ ", licenceId=" + licenceId + ", licenceValidUntilDate="
				+ licenceValidUntilDate + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result
				+ ((licenceId == null) ? 0 : licenceId.hashCode());
		result = prime
				* result
				+ ((licenceValidUntilDate == null) ? 0 : licenceValidUntilDate
						.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (getId() != other.getId())
			return false;
		if (licenceId == null) {
			if (other.licenceId != null)
				return false;
		} else if (!licenceId.equals(other.licenceId))
			return false;
		if (licenceValidUntilDate == null) {
			if (other.licenceValidUntilDate != null)
				return false;
		} else if (!licenceValidUntilDate.equals(other.licenceValidUntilDate))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
