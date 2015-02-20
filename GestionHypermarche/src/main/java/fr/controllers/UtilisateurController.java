package fr.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Controller;

import fr.models.Role;
import fr.models.Utilisateur;
import fr.models.Rayon;
import fr.services.impl.CrudUtilisateurImpl;
@Controller
@Path("/utilisateur")
@ManagedBean
@ViewScoped
public class UtilisateurController implements Serializable {
	
private static final long serialVersionUID = 2L;
@ManagedProperty("#{crudUtilisateur}")
private CrudUtilisateurImpl crudUtilisateur;
private Utilisateur utilisateur=new Utilisateur();

private  List<Utilisateur> lUtilisateur;
private Role role;
@PostConstruct
public void init(){

	crudUtilisateur=new CrudUtilisateurImpl();
	lUtilisateur=crudUtilisateur.allUtilisateur();
}
public String loadUtilisateur(Rayon rayon){
	
	lUtilisateur=crudUtilisateur.allUtilisateur();
	setlUtilisateur(lUtilisateur);
	
	return "user";
}


public String login() {
		utilisateur=crudUtilisateur.getUtilisateurByLoginPwd(utilisateur.getEmail(), utilisateur.getPwd());
		if(utilisateur!=null){			
		System.out.println("connexion"+utilisateur);
		return "admin";
		}
		else{
			FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Utilisateur inexistant",null));
		
			return null;
		}
}
@POST
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String connexion(@FormParam("email") String email,@FormParam("pwd") String pwd) {
		utilisateur=crudUtilisateur.getUtilisateurByLoginPwd(email, pwd);
		if(utilisateur!=null){			
		System.out.println("connexion"+utilisateur);
		return utilisateur.getEmail()+utilisateur.getId();
		}
		else{
			
			return "error";
		}
}
public void deconnecter(){
	
}
public Utilisateur getProfil(){
	return utilisateur;
}
public String saveUtilisateur() {	
	  
	    crudUtilisateur.updateUtilisateur(utilisateur);
	    System.out.println(utilisateur);
		utilisateur=new Utilisateur();
		lUtilisateur=crudUtilisateur.allUtilisateur();
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur Ajouter",null));
	 return null;
	 }
public void deleteUtilisateur(Utilisateur utilisateur){
	crudUtilisateur.deleteUtilisateur(utilisateur);
	lUtilisateur=crudUtilisateur.allUtilisateur();
	FacesContext.getCurrentInstance().addMessage
	(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur Supprimer",null));

}

public void clear(){
	utilisateur=new Utilisateur();
}
public Utilisateur getUtilisateur() {
	return utilisateur;
}
public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}
public List<Utilisateur> getlUtilisateur() {
	return lUtilisateur;
}
public void setlUtilisateur(List<Utilisateur> lUtilisateur) {
	this.lUtilisateur = lUtilisateur;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}




}
