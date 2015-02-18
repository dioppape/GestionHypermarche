package fr.services;

import java.util.List;

import fr.models.Article;
import fr.models.Categorie;

public interface CrudArticle {
	public String updateArticle(Article article);	
	public List< Article> getArticleByCategorie(Categorie categorie);
	public List< Article> getAllArticle();
	public Article getArticle(String codeBar);
	public String deleteArticle(Article article);
	}
