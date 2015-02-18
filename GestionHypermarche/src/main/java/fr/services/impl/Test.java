package fr.services.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.models.Role;
import fr.models.Utilisateur;
import fr.services.CrudUtilisateur;


public class Test {
	
	public static void main(String[] args) {
		
		
	
		
		Utilisateur user=new Utilisateur();
		user.setNom("Diallo");
		user.setPrenom("Miya");
		user.setId("001");
		user.setEmail("diallo.mb@gmail.com");
		user.setTel("0619462854");
		
		user.setPwd("0000");
		
		Role role=new Role();
		role.setId("100");
		role.setNom("ADMIN");
		
	
		user.getRole().add(role);
		CrudUtilisateur cruduser=new CrudUtilisateurImpl();
		CrudArticleImpl car=new CrudArticleImpl();
		//System.out.println(car.getArticle("10"));
//		System.out.println("all");
//		System.out.println(car.getArticle());
		//System.out.println(cruduser.addUtilisateur(user));
		//System.out.println(cruduser.getUtilisateurById("001").getNom());
		//System.out.println(cruduser.getUtilisateurByLoginPwd("diopref", "0000").getNom());
	/*	Rayon rayon=new Rayon();
		
		rayon.setId("1");
		rayon.setNom("Fruits");
		CrudRayonImpl crudRayon=new CrudRayonImpl();
		CrudCategorie crudCategorie=new CrudCategorie();
		//System.out.println(crudRayon.addRayon(rayon));
		
		//System.out.println(crudRayon.deleteRayon("2"));
		//System.out.println(crudRayon.updateRayon("2", rayon));
		System.out.println(crudRayon.getRayonById("0").getNom());
		Categorie categorie=new Categorie();
		categorie.setId("00");
		categorie.setType("Bio");
		//System.out.println(crudCategorie.addCategorie(categorie, "Fruits"));
		System.out.println(crudCategorie.getAllCategorie());
		Article art=new Article();
		art.setCodeBar("001");
		art.setLibelle("fruit");
		art.setCouleur("rouge");
		art.setPrix("1 euros");
		

CrudAricle crud=new CrudAricle();

//System.out.println(crud.addArticle(art));
//System.out.println(crud.deleteArticle("0000001"));
/*

crud.getAricle();

Article arti=new Article();
arti.setNom("poisson");
arti.setType("frais");
System.out.println(crud.updateArticle("poison", arti));*/
	}

}

