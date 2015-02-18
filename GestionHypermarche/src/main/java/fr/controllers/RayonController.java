package fr.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.models.Categorie;
import fr.models.Rayon;
import fr.models.Utilisateur;
import fr.services.impl.CrudRayonImpl;

@Controller
@RequestMapping("/spring")
@ManagedBean
@ViewScoped
public class RayonController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{crudRayon}")
	private  CrudRayonImpl crudRayon;//
	private  Rayon rayon=new Rayon();
	private  List<Rayon> lrayon;
	private Categorie categorie=new Categorie();
	 private  List<Categorie> lCategorie;
	 private Utilisateur utilisateur=new Utilisateur();
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

	@PostConstruct
	public void loadRayon(){
		crudRayon=new CrudRayonImpl();
		lrayon=crudRayon.allRayon();
		
	}
	
	public String saveRayon() {	
		     rayon.setUtilisateur(utilisateur);
		    crudRayon.updateRayon(rayon);
			rayon=new Rayon();
			lrayon=crudRayon.allRayon();
			FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Rayon Ajouter",null));
		 return null;
		 }
	public void deleteRayon(Rayon rayon){
		
		crudRayon.deleteRayon(rayon);
		lrayon=crudRayon.allRayon();
		FacesContext.getCurrentInstance().addMessage
		(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Rayon Supprimer",null));
	
	}

	public void clear(){
		rayon=new Rayon();
	}
	 public Rayon getRayon() {
		return rayon;
	}
	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
	public List<Rayon> getLrayon() {
		return lrayon;
	}
	public void setLrayon(List<Rayon> lrayon) {
		this.lrayon = lrayon;
	}
	
	
	
	
	
	
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@RequestMapping("/test")
	    public ModelAndView getAdmin() {
		  ModelAndView mv=new  ModelAndView("test");
	       mv.addObject("test","ce ci est un test");
	       return mv;
	    }
	/* @RequestMapping(method=RequestMethod.GET,value= "/route",produces=MediaType.APPLICATION_XHTML_XML_VALUE)
	   public String getXml() {
		 Rayon rayon=new Rayon();
		rayon= crudRayon.allRayon().get(0);
	        return rayon.getNom();
//new ResponseEntity<Rayon>(rayon,HttpStatus.OK);
	    }*/
	 @RequestMapping(value = "/some", method = RequestMethod.GET)
	 @ResponseBody
	 public String helloWorld()  {
	   return "Hello World";
	 }
	 @RequestMapping(value = "/something", method = RequestMethod.GET,produces=MediaType.APPLICATION_XML_VALUE)
	 @ResponseBody
	 public String sayXMLHello() {
		    return "<?xml version=\"1.0\"?>" + "<hello> Hello spring" + "</hello>";
		  }
	 @RequestMapping(method=RequestMethod.GET,value= "/allRayon",produces=MediaType.APPLICATION_XHTML_XML_VALUE)
	 @ResponseBody  
	 public List<Rayon> listRayon() {
		List<Rayon> lrayon= crudRayon.allRayon();
			
		 return lrayon;
		 }
	 @RequestMapping(method=RequestMethod.GET,value= "/addRayon",produces=MediaType.APPLICATION_XHTML_XML_VALUE)
	 @ResponseBody  
	 public String addRayon() {
		   
			
		 return null;
		 }
}
