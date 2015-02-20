package fr.beans;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Controller;

import fr.models.Utilisateur;
import fr.services.CrudUtilisateur;
import fr.services.impl.CrudUtilisateurImpl;


@Controller
@ManagedBean(name = "loginBean")
@ViewScoped

public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String username;
	private String password;
	CrudUtilisateur guser;


	private Utilisateur user=new Utilisateur();

	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public String login() {
		guser=new CrudUtilisateurImpl();
		
			user=guser.getUtilisateurByLoginPwd(getUsername(), getPassword());
			if(user!=null){			
			System.out.println("connexion"+password);
			return "admin";
			}
			else{
				FacesContext.getCurrentInstance().addMessage
				(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Utilisateur inexistant",null));
				return null;
			}
			
			
			
		
		
	}
public void update(){
	
}
public void getRayon() {
	
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

}
