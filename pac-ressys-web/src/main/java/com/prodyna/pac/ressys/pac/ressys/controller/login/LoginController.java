/**
 * 
 */
package com.prodyna.pac.ressys.pac.ressys.controller.login;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.prodyna.pac.ressys.pac.ressys.util.PacRessysRestClientProducer;
import com.prodyna.pac.ressys.usermgmt.model.User;
import com.prodyna.pac.ressys.usermgmt.service.UserService;

/**
 * Controller managed bean for login form.
 * 
 * @author Andreas Heizenreder (PRODYNA AG)
 *
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

	/**
	 * generated id for serialization.
	 */
	private static final long serialVersionUID = -4635372486176470653L;

	/**
	 * Value for navigation rules for loggout state.
	 */
	public static final String NAVIGATION_LOGGED_OUT = "loggedOut";

	/**
	 * Value for navigation rules for loggedin state.
	 */
	public static final String NAVIGATION_LOGGED_IN = "loggedIn";

	@Inject
	private FacesContext facesContext;

	private Logger log = Logger.getLogger(LoginController.class.getName());

	private String loginName;

	private String password;

	private User loginUser;

	private boolean loggedIn;

	public User getLoginUser() {
		return loginUser;
	}

	public String login() throws Exception {
		String result = LoginController.NAVIGATION_LOGGED_OUT;

		try {
			HttpServletRequest request = (HttpServletRequest) facesContext
					.getExternalContext().getRequest();
			// login to the system
			request.login(loginName, password);

			loggedIn = true;
			// when login was successful then get the user by name
			UserService userService = PacRessysRestClientProducer
					.getPacRessysService(loginName, password, UserService.class);
			loginUser = userService.findByLoginName(loginName);
			result = LoginController.NAVIGATION_LOGGED_IN;

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Please check your login name and password!",
					"Login failed");
			facesContext.addMessage(null, m);
		}

		return result;
	}

	public String logout() throws Exception {
		String result = NAVIGATION_LOGGED_OUT;

		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.invalidateSession();

		loggedIn = false;
		loginUser = new User();
		loginName = "";
		password = "";

		return result;
	}

	/**
	 * check the group of the current user and decides, if the actions for this
	 * user are allowed.
	 * 
	 * @return true if the user is authorized to execute Actions on system.
	 */
	public boolean getActionsAllowed() {
		boolean result = false;

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		result = request.isUserInRole("Admin");
		result = result || request.isUserInRole("User");

		return result;
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
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

}
