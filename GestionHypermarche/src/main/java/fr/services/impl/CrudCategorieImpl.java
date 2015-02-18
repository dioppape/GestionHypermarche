package fr.services.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import fr.models.*;
import fr.services.CrudCategorie;
import fr.services.CrudRayon;
@Service
public class CrudCategorieImpl implements CrudCategorie {
	@Autowired
	JAXBContext context = null;
	private String outputFile="categorie.xml";
	private String packages="fr.models";
	Marshaller marsh;
	ObjectFactory factory;
	Categories hyper;
	Rayon rayon;
	CrudRayonImpl crudRayon;
	private List<Categorie> list;
	public CrudCategorieImpl(){
     factory=new ObjectFactory();
	 hyper=factory.createCategories();
	
	 crudRayon=new CrudRayonImpl();
	}
	@Override
public String updateCategorie(Categorie categorie){
	
	String result="error";
	
	
	try {
		//unmachaller
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		File file=new File(outputFile);
		if(file.exists()){
		hyper=(Categories) unmarsh.unmarshal(file);
		 list=hyper.getCategorie();
		}
		else{
			hyper=new Categories();
			list=new ArrayList<Categorie>();
		}
		//add article
		
		boolean existe=false;
		int i=0;
		while(i<list.size()){

			if(list.get(i).getId().equals(categorie.getId())){
				list.set(i, categorie);
				result="Categorie id "+list.get(i).getId()+" updated";
				 existe= true;
			}
			i++;
		}
		if(!existe){
			
		list.add(categorie);
		}
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
public List<Categorie> getAllCategorieDom(){
	XPathFactory pathFactory=XPathFactory.newInstance();
	XPath xpath = pathFactory.newXPath();
	try {
		XPathExpression expr = xpath.compile("//Categorie[@Type='Bio']");
		File fXmlFile = new File(outputFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// use DTD validation
		dbFactory.setValidating(true);
		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// report Errors if DTD validation is On
		 // note: ErrorHandler must be defined
		
		dBuilder.setErrorHandler(null);
	 Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
		
		NodeList nList = (NodeList)expr.evaluate(doc,XPathConstants.NODESET);
		
		 for (int i = 0; i < nList.getLength(); i++) {
		Node nNode = nList.item(i);
		 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		// your code here
			System.out.println("name= "+nNode.getAttributes().item(1).getNodeValue());
	}
		 }
		 } catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
	@Override
	public Categorie getCategorieByType(String type){
		Categorie categorie=null;
		XPathFactory pathFactory=XPathFactory.newInstance();
		XPath xpath = pathFactory.newXPath();
		try {
			XPathExpression expr = xpath.compile("//Rayon/Categorie[@Type="+type+"]");
			File fXmlFile = new File(outputFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// use DTD validation
			dbFactory.setValidating(true);
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// report Errors if DTD validation is On
			 // note: ErrorHandler must be defined
			
			dBuilder.setErrorHandler(null);
		 Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
			
			NodeList nList = (NodeList)expr.evaluate(doc,XPathConstants.NODESET);
			
			 for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			// your code here
				System.out.println("name= "+nNode.getAttributes().item(1).getNodeValue());
		}
			 }
			 } catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}
	@Override
public List< Categorie> getCategorieByRayon(Rayon rayon){
	
	List<Categorie> listCategorie=new ArrayList<Categorie>();
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Categories) unmarsh.unmarshal(new File(outputFile));
		 list=hyper.getCategorie();
		 int i=0;
			while(i<list.size()){
			if(list.get(i).getRayon().getId().equals(rayon.getId()))
				listCategorie.add(list.get(i));
			i++;
			}
		
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listCategorie;
}



@Override
public List<Categorie> getAllCategorie() {
	List<Categorie> listCategorie=new ArrayList<Categorie>();
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		 hyper=(Categories) unmarsh.unmarshal(new File(outputFile));
		
		listCategorie=hyper.getCategorie();
		
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listCategorie;
}

@Override
public String deleteCategorie(Categorie categorie) {
	String result="nothing to update";
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		hyper=(Categories) unmarsh.unmarshal(new File(outputFile));
		
		List<Categorie> lcat = hyper.getCategorie();
		int j=0;
		while(j<lcat.size()){
		if(categorie.getId().equals(lcat.get(j).getId())){
			lcat.remove(j);
			CrudArticleImpl crudar=new CrudArticleImpl();
			crudar.deleteArticle(categorie.getId());
			result="categorie name "+categorie.getId()+" deleted";
		}	
		j++;	
			
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
public String deleteCategorie(String id) {
	String result="nothing to update";
	try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		hyper=(Categories) unmarsh.unmarshal(new File(outputFile));
		
			List<Categorie> lcat = hyper.getCategorie();
			//int j=0;
			int j = lcat.size()-1;
			while(j>=0){
			if(id.equals(lcat.get(j).getRayon().getId())){
				
				CrudArticleImpl crudar=new CrudArticleImpl();
				crudar.deleteArticle(lcat.get(j).getId());
				lcat.remove(j);
				result="categorie name "+id+" deleted";
			}	
			j--;
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
public int[] getCategoriePos(Categorie categorie) {
	
	int catpos[];
	catpos=new int[2];


	return catpos;
}
}
