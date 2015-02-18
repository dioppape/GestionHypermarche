package fr.services;

import java.util.List;

import fr.models.Role;
import fr.models.Utilisateur;

public interface CrudUtilisateur {
	public String updateUtilisateur(Utilisateur user);
	public String deleteUtilisateur(Utilisateur user);
	public Utilisateur getUtilisateurById(String id);
	public Utilisateur getUtilisateurByLoginPwd(String login,String password);
	public int getUtilisateurPosByName(String name);
	public List<Utilisateur> allUtilisateur();
	public List<Utilisateur> getUtilisateurByRole(Role role);
}
