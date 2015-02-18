package fr.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import fr.models.*;
import fr.services.CrudUtilisateur;
@Service
public class CrudUtilisateurImpl implements CrudUtilisateur {
	@Autowired
	JAXBContext context = null;
	private String outputFile="utilisateur.xml";
	private String packages="fr.models";
	Marshaller marsh;
	ObjectFactory factory;
	Utilisateurs hyper;

	@Override
	public String updateUtilisateur(Utilisateur user) {
		String result="error";
		
		List<Utilisateur> list=null;
		try {
			//unmachaller
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			File file=new File(outputFile);
			if(file.exists()){
			hyper=(Utilisateurs) unmarsh.unmarshal(file);
			list=hyper.getUtilisateur();
			}
			else{
				hyper=new Utilisateurs();
				 list=new ArrayList<Utilisateur>();
			}
			//add article
			int i=0;
			
			boolean existe=false;
			while(i<list.size()){

				if(list.get(i).getId().equals(user.getId())){
					//list.get(i).setId(rayon.getId());
					list.set(i, user);			
				 existe= true;
				}
				i++;
			}
			if(!existe)
	        hyper.getUtilisateur().add(user);
			result="New Rayon added";
	        //mashaler
			marsh = context.createMarshaller();
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marsh.marshal(hyper, new File(outputFile));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	
	@Override
	public String deleteUtilisateur(Utilisateur user) {
		String result="nothing to update";
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
			int i=0;
			List<Utilisateur> list =hyper.getUtilisateur() ;
			while(i<list.size()){

				if(list.get(i).getId().equals(user.getId())){
					list.remove(i);
					result="Utilisateur name "+user.getNom()+" deleted";
				}	
				i++;
		}

			marsh = context.createMarshaller();
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marsh.marshal(hyper, new File(outputFile));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Utilisateur getUtilisateurById(String id) {
		List<Utilisateur> listUtilisateur=new ArrayList<Utilisateur>();
		Utilisateur utilisateur=null;
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
		listUtilisateur=hyper.getUtilisateur();
		int i=0;
		while(i<listUtilisateur.size()){
			if(listUtilisateur.get(i).getId().equals(id)){
				utilisateur=listUtilisateur.get(i);
			    break;
			}
			else {
				i++;
			}
		}
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return utilisateur;
	}

	@Override
	public int getUtilisateurPosByName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utilisateur> allUtilisateur() {
		List<Utilisateur> listUtilisateur=null;
		
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
		listUtilisateur=hyper.getUtilisateur();
		
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return listUtilisateur;
	}

	@Override
	public Utilisateur getUtilisateurByLoginPwd(String login, String password) {
		List<Utilisateur> listUtilisateur=new ArrayList<Utilisateur>();
		Utilisateur utilisateur=null;
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
		listUtilisateur=hyper.getUtilisateur();
		int i=0;
		while(i<listUtilisateur.size()){
			if(listUtilisateur.get(i).getEmail().equals(login) && listUtilisateur.get(i).getPwd().equals(password)){
				utilisateur=listUtilisateur.get(i);
			    break;
			}
			else {
				i++;
			}
		}
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return utilisateur;
	}

	@Override
	public List<Utilisateur> getUtilisateurByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getUtilisateurPosById(String id) {
		List<Utilisateur> listuser=null;
		int userpos=-1;
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
		listuser=hyper.getUtilisateur();
		int i=0;
		while(i<listuser.size()){
			if(listuser.get(i).getId().equals(id)){
				userpos=i;
			    break;
			}
			else {
				i++;
			}
		}
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return userpos;
	}

}
