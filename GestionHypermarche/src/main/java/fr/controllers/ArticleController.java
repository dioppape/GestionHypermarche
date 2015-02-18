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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import fr.models.Article;
import fr.models.Articles;
import fr.models.Categorie;
import fr.models.Utilisateur;
import fr.services.impl.CrudArticleImpl;
@Controller
@Path("/article")
@ManagedBean
@ViewScoped
public class ArticleController implements Serializable {
	
private static final long serialVersionUID = 3L;
@ManagedProperty("#{crudArticle}")
private CrudArticleImpl crudArticle;
private Categorie categorie=new Categorie();
private Article article=new Article();
private  List<Article> lArticle;
private Utilisateur user;
@PostConstruct
public void init(){

	crudArticle=new CrudArticleImpl();
	lArticle=crudArticle.getAllArticle();
}


public String loadArticle(Categorie categorie){
	
	lArticle=crudArticle.getArticleByCategorie(categorie);
	setlArticle(lArticle);
	
	return "article";
}

public String saveArticle() {	
	    article.setCategorie(categorie);
	    crudArticle.updateArticle(article);
		article=new Article();
		lArticle=crudArticle.getArticleByCategorie(categorie);
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Article Ajouter",null));
	 return null;
	 }
public void deleteArticle(Article article){
	crudArticle.deleteArticle(article);
	lArticle=crudArticle.getArticleByCategorie(categorie);
	FacesContext.getCurrentInstance().addMessage
	(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Article Supprimer",null));

}
@Path("{codebar}")
@GET
@Produces({MediaType.APPLICATION_XML})
public Articles getArticle(@PathParam("codebar") String codebar) {
	crudArticle=new CrudArticleImpl();
	article=crudArticle.getArticle(codebar);
	Articles articles = new Articles();
	articles.getArticle().add(article);
	return articles;
  // return "<article> <codebar>"+article.getCodebar()+"<codebar/> <libelle>"+article.getLibelle()+"<libelle/>  <prixvente>"+article.getPrixvente()+"<prixvente/><couleur>"+article.getCouleur()+"<couleur/><article/>";

}
@POST
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String aVenteArticle(@FormParam("codebar") String codebar,@FormParam("libelle") String libelle,@FormParam("prixdevente") String prixdevente,@FormParam("quantite") String quantite) {	
	article=new Article();
	categorie=new Categorie();
	crudArticle=new CrudArticleImpl();
	article.setCodebar(codebar);
	article.setLibelle(libelle);
	article.setPrixvente(prixdevente);
	categorie.setId("0");
	article.setQuantite(quantite);
	article.setCategorie(categorie);
    crudArticle.updateArticle(article);
	
 return "success";
 }
@Path("/vendu")
@POST
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String venduArticle(@FormParam("codebar") String codebar,@FormParam("libelle") String libelle,@FormParam("prixvente") String prixvente,@FormParam("quantite") String quantite,@FormParam("idcaissier") String idcaissier) {	
	article=new Article();
	user=new Utilisateur();
	user.setId(idcaissier);
	crudArticle=new CrudArticleImpl();	
	article=crudArticle.getArticle(codebar);
	article.setUtilisateur(user);
	article.setQuantite(quantite);
    crudArticle.updateArticle(article);
	
 return "success";
 }
public void clear(){
	article=new Article();
}
public Article getArticle() {
	return article;
}
public void setArticle(Article article) {
	this.article = article;
}
public List<Article> getlArticle() {
	return lArticle;
}
public void setlArticle(List<Article> lArticle) {
	this.lArticle = lArticle;
}
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}




}
