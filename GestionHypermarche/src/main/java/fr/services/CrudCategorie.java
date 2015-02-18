package fr.services;

import java.util.List;

import fr.models.*;

public interface CrudCategorie {
	public String updateCategorie(Categorie categorie);	
	public List< Categorie> getCategorieByRayon(Rayon rayon);
	public List< Categorie> getAllCategorie();
	public String deleteCategorie(Categorie categorie);
	public int[] getCategoriePos(Categorie categorie);
	public List<Categorie> getAllCategorieDom();

	public Categorie getCategorieByType(String type);
}
