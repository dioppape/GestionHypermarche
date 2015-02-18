package fr.services;

import java.util.List;

import fr.models.Role;
import fr.models.Utilisateur;

public interface CrudRole {
public List<Role> getRole(Utilisateur  user);
public String updateRole(Role role,Utilisateur  user);
public String deleteRole(Role role,Utilisateur  user);
public List<Role> getRole( Role role);



}
