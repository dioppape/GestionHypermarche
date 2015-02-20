package fr.services.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import fr.models.*;
import fr.services.CrudRole;
@Service
public class CrudRoleImpl implements CrudRole {
	@Autowired
	JAXBContext context = null;
	private String outputFile="utilisateur.xml";
	private String packages="fr.models";
	Marshaller marsh;
	ObjectFactory factory;
	Utilisateurs hyper;
	CrudUtilisateurImpl cruduser;
	@Override
	public List<Role> getRole(Utilisateur user) {
		List<Role> listCategorie=null;
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			 hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
			 CrudUtilisateurImpl cuser=new CrudUtilisateurImpl();
			if(cuser.getUtilisateurPosById(user.getId())!=-1)
			listCategorie=hyper.getUtilisateur().get(cuser.getUtilisateurPosById(user.getId())).getRole();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCategorie;
	}

	@Override
	public String updateRole(Role role,Utilisateur  user) {
		String result="error";
		
		cruduser=new CrudUtilisateurImpl();

		List<Role> list=null;
		try {
			//unmachaller
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			File file=new File(outputFile);
			if(file.exists()){
			hyper=(Utilisateurs) unmarsh.unmarshal(file);
			list=hyper.getUtilisateur().get(cruduser.getUtilisateurPosById(user.getId())).getRole();
			}
			else{
				hyper=new Utilisateurs();
				list=hyper.getUtilisateur().get(0).getRole();
			}
			//add article
			 
			boolean existe=false;
			int i=0;
			while(i<list.size()){

				if(list.get(i).getNom().equals(role.getNom())){
					//list.get(i).setId(rayon.getId());
					list.set(i, role);
					result="Categorie id "+list.get(i).getNom()+" updated";
					 existe= true;
				}
				i++;
			}
			if(!existe)
			list.add(role);
		  result="New Categorie added";
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
	public String deleteRole(Role role,Utilisateur  user) {
		String result="nothing to update";
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			hyper=(Utilisateurs) unmarsh.unmarshal(new File(outputFile));
			int i=0;
			List<Utilisateur> list =hyper.getUtilisateur() ;
			while(i<list.size()){
				List<Role> lrole = list.get(i).getRole();
				int j=0;
				while(j<lrole.size()){
				if(lrole.get(j).getNom().equals(role.getNom())){
					lrole.remove(j);
					}	
				j++;
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
	public List<Role> getRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
