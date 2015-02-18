package fr.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.models.*;
import fr.services.CrudRayon;
@Service
public class CrudRayonImpl implements CrudRayon {
	@Autowired
	JAXBContext context = null;
	private String outputFile="rayon.xml";
	private String packages="fr.models";
	Marshaller marsh;
	ObjectFactory factory;
	Rayons hyper;
	Rayon rayon;
	public CrudRayonImpl(){
     factory=new ObjectFactory();
	 rayon=factory.createRayon();
	}


@Override
public String updateRayon(Rayon rayon){
	List<Rayon> list=null;
	String result="nothing to update";
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		File file=new File(outputFile);
		if(file.exists()){
			hyper=(Rayons) unmarsh.unmarshal(file);
			list=hyper.getRayon();	
		
		}
		else{
			hyper=new Rayons();
			list=new ArrayList<Rayon>();
		}
		int i=0;
		
		boolean existe=false;
		while(i<list.size()){

			if(list.get(i).getId().equals(rayon.getId())){
				list.set(i, rayon);		
				result="Rayon id "+list.get(i).getId()+" updated";
				 existe= true;
			}
			i++;
		}
		if(!existe)
			 hyper.getRayon().add(rayon);
       //mashaller
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
public String deleteRayon(Rayon rayon){
	
	String result="nothing to update";
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		hyper=(Rayons) unmarsh.unmarshal(new File(outputFile));
		int i=0;
		List<Rayon> list =hyper.getRayon() ;
		while(i<list.size()){

			if(list.get(i).getId().equals(rayon.getId())){
				list.remove(i);
				CrudCategorieImpl crudcat=new CrudCategorieImpl();
				crudcat.deleteCategorie(rayon.getId());
				result="article name "+rayon.getNom()+" deleted";
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
public Rayon getRayonById(String id){
	List<Rayon> listRayon=new ArrayList<Rayon>();
	Rayon rayon=null;
try {
	context=JAXBContext.newInstance(packages);
	Unmarshaller unmarsh=context.createUnmarshaller();
	 hyper=(Rayons) unmarsh.unmarshal(new File(outputFile));
	listRayon=hyper.getRayon();
	int i=0;
	while(i<listRayon.size()){
		if(listRayon.get(i).getId().equals(id)){
			rayon=listRayon.get(i);
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

	return rayon;
}
@Override
public int getRayonPosById(String id){
	List<Rayon> listRayon=new ArrayList<Rayon>();
	int rayonpos=-1;
try {
	context=JAXBContext.newInstance(packages);
	Unmarshaller unmarsh=context.createUnmarshaller();
	 hyper=(Rayons) unmarsh.unmarshal(new File(outputFile));
	listRayon=hyper.getRayon();
	int i=0;
	while(i<listRayon.size()){
		if(listRayon.get(i).getId().equals(id)){
			rayonpos=i;
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

	return rayonpos;
}

@Override
public List<Rayon> allRayon() {
	
	List<Rayon> listRayon=new ArrayList<Rayon>();
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Rayons) unmarsh.unmarshal(new File(outputFile));
		listRayon=hyper.getRayon();
		
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listRayon;
}

}
