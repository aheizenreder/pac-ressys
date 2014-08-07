/**
 * 
 */
package com.prodyna.pac.ressys.usermgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity for user representation.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = User.SELECT_ALL_USER, query = "SELECT a FROM User a"),
		@NamedQuery(name = User.FIND_USER, query = "Select u FROM User u where u.loginName = :loginName and u.password = :password"),
		@NamedQuery(name = User.FIND_USER_BY_LOGIN_NAME, query = "Select u FROM User u where u.loginName = :loginName") })
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(name = "user_key", columnNames = { "login_name" }) })
public class User implements Serializable {

	/**
	 * Name of the query for select all user.
	 */
	public static final String SELECT_ALL_USER = "selectAllUser";

	/**
	 * Name of the query for find a user by login name and password;
	 */
	public static final String FIND_USER = "findUser";

	/**
	 * Name of parameter for login name in findUser query
	 */
	public static final String FIND_USER_PARAMETER_NAME_LOGIN_NAME = "loginName";

	/**
	 * Name of parameter for password in findUser query
	 */
	public static final String FIND_USER_PARAMETER_NAME_PASSWORD = "password";

	/**
	 * Name of the query for searching for a user by his/her login name.
	 */
	public static final String FIND_USER_BY_LOGIN_NAME = "findUserByLoginName";

	/**
	 * Name of parameter for login name in findUserByLoginName query
	 */
	public static final String FIND_USER_BY_LOGIN_NAME_PARAMETER_NAME_LOGIN_NAME = "loginName";

	/**
	 * generated value for serialization.
	 */
	private static final long serialVersionUID = 8264167079588857563L;

	/**
	 * Key field for user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;

	@NotNull
	@Column(name = "login_name")
	@Size(min = 3, max = 20)
	private String loginName;

	@NotNull
	@Size(min = 7)
	private String password;

	@NotNull
	@Column(name = "email")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
			+ "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
			+ "(?:[a-z0-9-]*[a-z0-9])?", message = "Invalid eMail!")
	private String eMail;

	@NotNull
	@Column(name = "user_name")
	@Size(min = 1, max = 20)
	private String userName;

	@Column(name = "licence_id")
	private String licenceId;

	@Column(name = "licence_valid_until_date")
	private Date licenceValidUntilDate;

	@Transient
	private boolean passwordEncrypted;

	/**
	 * 
	 */
	public User() {
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
		this.id = id;
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.eMail = eMail;
		this.licenceId = licenceId;
		this.licenceValidUntilDate = licenceValidUntilDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
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
	 * @param loginName
	 *            the loginName to set
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
	 * @param password
	 *            the password to set
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
	 * @param eMail
	 *            the eMail to set
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
	 * @param licenceId
	 *            the licenceId to set
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
	 * @param licenceValidUntilDate
	 *            the licenceValidUntilDate to set
	 */
	public void setLicenceValidUntilDate(Date licenceValidUntilDate) {
		this.licenceValidUntilDate = licenceValidUntilDate;
	}

	/**
	 * @return the passwordEncrypted
	 */
	public boolean isPasswordEncrypted() {
		return passwordEncrypted;
	}

	/**
	 * @param passwordEncrypted
	 *            the passwordEncrypted to set
	 */
	public void setPasswordEncrypted(boolean passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password="
				+ password + ", eMail=" + eMail + ", userName=" + userName
				+ ", licenceId=" + licenceId + ", licenceValidUntilDate="
				+ licenceValidUntilDate + ", passwordEncrypted="
				+ passwordEncrypted + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		result = prime * result + (passwordEncrypted ? 1231 : 1237);
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (passwordEncrypted != other.passwordEncrypted)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
