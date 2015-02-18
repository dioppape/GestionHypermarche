package fr.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.models.Role;
import fr.models.Utilisateur;
import fr.services.impl.CrudRoleImpl;
@Controller
@RequestMapping("/role")
@ManagedBean
@ViewScoped
public class RoleController implements Serializable {
	
private static final long serialVersionUID = 2L;
@ManagedProperty("#{crudRole}")
private CrudRoleImpl crudRole;
private Utilisateur utilisateur=new Utilisateur();
private Role role=new Role();

private  List<Role> lRole;
@PostConstruct
public void init(){

	crudRole=new CrudRoleImpl();
	lRole=crudRole.getRole(utilisateur);
}
public String loadRole(Utilisateur utilisateur){
	
	lRole=crudRole.getRole(utilisateur);
	setlRole(lRole);
	
	return "role";
}

public String saveRole() {	
	  
	    crudRole.updateRole(role, utilisateur);
		role=new Role();
		lRole=crudRole.getRole(utilisateur);
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Role Ajouter",null));
	 return null;
	 }
public void deleteRole(Role role){
	crudRole.deleteRole(role,utilisateur);
	lRole=crudRole.getRole(utilisateur);
	FacesContext.getCurrentInstance().addMessage
	(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Role Supprimer",null));

}

public void clear(){
	role=new Role();
}
public Role getRole() {
	return role;
}
public void setRole(Role Role) {
	this.role = Role;
}
public List<Role> getlRole() {
	return lRole;
}
public void setlRole(List<Role> lRole) {
	this.lRole = lRole;
}

public Utilisateur getUtilisateur() {
	return utilisateur;
}

public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}


}
