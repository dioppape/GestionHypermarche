package fr.beans;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		/*user.setNom("Diop");
		user.setPrenom("Pape");
		user.setId("001");
		user.setEmail("diopref@gmail.com");
		user.setTel("0619462854");
		user.setLogin("diopref");
		user.setPwd("00000");
		Adresse adrese = new Adresse();
		adrese.setCodePostal(51100);
		adrese.setVille("Reims");
		adrese.setNomDeRue("ch roulier");
		adrese.setNumeroDeRue(1);
		adrese.setId("00001");
		Role role=new Role();
		role.setId("100");
		role.setNom("ADMIN");
		user.setAdresse(adrese);
		user.getRole().add(role);
		
			System.out.println(guser.addUtilisateur(user));*/
			user=guser.getUtilisateurByLoginPwd(getUsername(), getPassword());
			if(user!=null){			
			System.out.println("connexion"+password);
			return "admin";
			}
			else{
				System.out.println("fin dialog");
			RequestContext.getCurrentInstance().execute(
					"PF('finalDialog').show();");
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
