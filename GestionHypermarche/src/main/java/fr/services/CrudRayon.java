package fr.services;

import java.util.List;

import fr.models.*;

public interface CrudRayon {
	public String updateRayon(Rayon rayon);
	public String deleteRayon(Rayon rayon);
	public Rayon getRayonById(String id);
	public int getRayonPosById(String id);
	public List<Rayon> allRayon();
}
