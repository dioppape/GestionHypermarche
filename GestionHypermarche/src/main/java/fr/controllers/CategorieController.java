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

import fr.models.Categorie;
import fr.models.Rayon;
import fr.services.impl.CrudCategorieImpl;
@Controller
@RequestMapping("/categorie")
@ManagedBean
@ViewScoped
public class CategorieController implements Serializable {
	
private static final long serialVersionUID = 2L;
@ManagedProperty("#{crudCategorie}")
private CrudCategorieImpl crudCategorie;
private Rayon rayon=new Rayon();
private Categorie categorie=new Categorie();

private  List<Categorie> lCategorie;
@PostConstruct
public String getAllCategorie(){
	crudCategorie=new CrudCategorieImpl();
	setlCategorie(null);
	lCategorie=crudCategorie.getAllCategorie();
	setlCategorie(lCategorie);
	
	return "categorie";
}
public String loadCategorie(Rayon rayon){
	setlCategorie(null);
	lCategorie=crudCategorie.getCategorieByRayon(rayon);
	setlCategorie(lCategorie);
	
	return "categorie";
}


public String saveCategorie() {	
	    categorie.setRayon(rayon);
	    crudCategorie.updateCategorie(categorie);
		categorie=new Categorie();
		lCategorie=crudCategorie.getCategorieByRayon(rayon);
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Categorie Ajouter",null));
	 return null;
	 }
public void deleteCategorie(Categorie categorie){
	crudCategorie.deleteCategorie(categorie);
	lCategorie=crudCategorie.getCategorieByRayon(rayon);
	FacesContext.getCurrentInstance().addMessage
	(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Categorie Supprimer",null));

}

public void clear(){
	categorie=new Categorie();
}
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}
public List<Categorie> getlCategorie() {
	return lCategorie;
}
public void setlCategorie(List<Categorie> lCategorie) {
	this.lCategorie = lCategorie;
}

public Rayon getRayon() {
	return rayon;
}

public void setRayon(Rayon rayon) {
	this.rayon = rayon;
}


}
